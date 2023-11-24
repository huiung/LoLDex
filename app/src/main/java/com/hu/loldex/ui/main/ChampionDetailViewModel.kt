package com.hu.loldex.ui.main

import com.hu.loldex.domain.GetChampionsUseCase
import com.hu.loldex.model.Champion
import com.hu.loldex.ui.base.MviIntent
import com.hu.loldex.ui.base.MviSingleEvent
import com.hu.loldex.ui.base.MviViewModel
import com.hu.loldex.ui.base.MviViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase
) : MviViewModel<ChampionDetailSingleEvent, ChampionDetailState, ChampionDetailIntent>() {

    override fun createInitialState(): ChampionDetailState = ChampionDetailState(isLoading = true)

    override fun reduceState(
        prevState: ChampionDetailState,
        intent: ChampionDetailIntent,
        ret: Any?,
        error: Throwable?
    ): ChampionDetailState {
        return when (intent) {
            is ChampionDetailIntent.GetChampion -> {
                prevState.copy(
                    isLoading = intent.useLoading,
                    error = error,
                    champion = (ret as List<Champion>).firstOrNull() ?: prevState.champion
                )
            }
        }
    }

    override fun onViewStateChanged(state: ChampionDetailState) {
        when {
            state.isLoading -> {
                sendSingleEvent(ChampionDetailSingleEvent.Loading)
            }
            state.error != null -> {

            }
        }
    }
    override suspend fun processUseCase(prevState: ChampionDetailState,  intent: ChampionDetailIntent): ChampionDetailState {
        return when (intent) {
            is ChampionDetailIntent.GetChampion -> {
                getChampionsUseCase.execute(
                    GetChampionsUseCase.Parameters(
                        version = intent.version,
                        language = intent.language,
                        championId = intent.championId
                    )
                )
            }
        }.mapToState(prevState, intent)
    }
}

data class ChampionDetailState(
    override val isLoading: Boolean = false,
    override val error: Throwable? = null,
    val champion: Champion? = null,
) : MviViewState
sealed class ChampionDetailIntent : MviIntent {
    data class GetChampion(
        val version: String, val language: String, val championId: String,
        override val useLoading: Boolean = false
    ) : ChampionDetailIntent()
}

sealed class ChampionDetailSingleEvent : MviSingleEvent {
    data object Loading : ChampionDetailSingleEvent()
}