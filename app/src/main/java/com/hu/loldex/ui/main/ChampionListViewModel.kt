package com.hu.loldex.ui.main

import com.hu.loldex.domain.GetChampionsUseCase
import com.hu.loldex.domain.GetVersionUseCase
import com.hu.loldex.model.Champion
import com.hu.loldex.model.Versions
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
class ChampionListViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getVersionUseCase: GetVersionUseCase
) : MviViewModel<ChampionListSingleEvent, ChampionListState, ChampionListIntent>() {

    val language: List<String> = listOf("ko_KR", "en_US")

    override fun createInitialState(): ChampionListState = ChampionListState(isLoading = true)
    override fun onViewStateChanged(state: ChampionListState) {
        when {
            state.isLoading -> {
                sendSingleEvent(ChampionListSingleEvent.Loading)
            }

            state.error != null -> {
                //
            }
        }
    }

    init {
        sendIntent(ChampionListIntent.GetVersions())
    }

    override fun reduceState(
        prevState: ChampionListState,
        intent: ChampionListIntent,
        ret: Any?,
        error: Throwable?
    ): ChampionListState {

        val champions = when (intent) {
            is ChampionListIntent.GetChampions -> (ret as List<Champion>?) ?: prevState.champions
            else -> prevState.champions
        }

        val versions = when (intent) {
            is ChampionListIntent.GetVersions -> (ret as Versions?)?.versions ?: prevState.versions
            else -> prevState.versions
        }

        return prevState.copy(
            isLoading = false,
            error = error,
            champions = champions,
            versions = versions
        )
    }

    override suspend fun processUseCase(
        prevState: ChampionListState,
        intent: ChampionListIntent,
    ): ChampionListState {
        return when (intent) {
            is ChampionListIntent.GetVersions -> {
                getVersionUseCase.execute(Unit)
                    .onSuccess {
                        sendIntent(
                            ChampionListIntent.GetChampions(
                                it.versions[0],
                                language.first()
                            )
                        )
                    }
            }

            is ChampionListIntent.GetChampions -> {
                getChampionsUseCase.execute(
                    GetChampionsUseCase.Parameters(
                        intent.version,
                        intent.language
                    )
                )
            }
        }.mapToState(prevState, intent)
    }
}

data class ChampionListState(
    override val isLoading: Boolean = false,
    override val error: Throwable? = null,
    val champions: List<Champion>? = null,
    val versions: List<String>? = null
) : MviViewState

sealed class ChampionListIntent : MviIntent {
    data class GetVersions(override val useLoading: Boolean = false) : ChampionListIntent()
    data class GetChampions(
        val version: String, val language: String,
        override val useLoading: Boolean = false
    ) : ChampionListIntent()
}

sealed class ChampionListSingleEvent : MviSingleEvent {
    data object Loading : ChampionListSingleEvent()
}