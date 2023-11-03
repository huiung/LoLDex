package com.hu.loldex.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.loldex.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningReduce
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
abstract class MviViewModel<E : MviSingleEvent, T: Model, R: Model,  I : MviIntent> : ViewModel() {

    private val eventChannel = Channel<E>()
    private val intentChannel = Channel<I>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<ViewState<T, R>> = intentChannel.receiveAsFlow()
        .flatMapMerge {
            processIntent(it)
        }
        .runningReduce { previous, current ->
            reduceState(previous, current)
        }
        .flowOn(Dispatchers.Default)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ViewState(isLoading = true))

    val singleEvent: Flow<E> = eventChannel.receiveAsFlow()
    abstract fun processIntent(intent: I) : Flow<ViewState<T, R>>

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private fun reduceState(previous: ViewState<T, R>, current: ViewState<T, R>): ViewState<T, R> {
        return previous.copy(
            dataList = current.dataList ?: previous.dataList,
            metaData = current.metaData ?: previous.metaData,
            isLoading = current.isLoading,
            error = current.error
        )
    }
}

data class ViewState<T, R> (
    val dataList: List<T>? = null,
    val metaData: R? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
interface MviSingleEvent
interface MviIntent
