package com.hu.loldex.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
 * Designed and developed by 2023 huiung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
abstract class MviViewModel<E : MviSingleEvent, S : MviViewState,  I : MviIntent> : ViewModel() {

    private val eventChannel = Channel<E>()
    private val intentChannel = Channel<I>()

    private val initialState by lazy { createInitialState() }

    abstract fun createInitialState(): S

    val viewState: StateFlow<S> = intentChannel.receiveAsFlow()
        .runningFold(initialState) { prev, intent ->
            val nextState = processUseCase(prev, intent)
            if (nextState.isLoading) { //
                intentChannel.send(intent)
            }
            onViewStateChanged(nextState)
            nextState
        }
        .flowOn(Dispatchers.Default)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), initialState)

    val singleEvent: Flow<E> = eventChannel.receiveAsFlow()
    abstract suspend fun processUseCase(prevState: S, intent: I) : S

    //send single event if needed
    protected abstract fun onViewStateChanged(state: S)
    protected abstract fun reduceState(prevState: S, intent: I, ret: Any? = null, error: Throwable? = null): S

    fun<T: Any> Result<T>.mapToState(prevState: S, intent: I): S {
        return when  {
            isSuccess -> reduceState(prevState, intent, ret = getOrNull())
            isFailure -> reduceState(prevState, intent, error = exceptionOrNull())
            else -> {
                throw IllegalStateException("Result must be either success or failure")
            }
        }
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    fun sendSingleEvent(singleEvent: E) {
        viewModelScope.launch {
            eventChannel.send(singleEvent)
        }
    }
}

interface MviViewState {
    val isLoading: Boolean
    val error: Throwable?
}
interface MviSingleEvent
interface MviIntent {
    val useLoading: Boolean
}
