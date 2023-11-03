package com.hu.loldex.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.loldex.domain.GetChampionsUseCase
import com.hu.loldex.domain.GetVersionUseCase
import com.hu.loldex.model.Champion
import com.hu.loldex.model.Versions
import com.hu.loldex.ui.base.MviIntent
import com.hu.loldex.ui.base.MviSingleEvent
import com.hu.loldex.ui.base.MviViewModel
import com.hu.loldex.ui.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
) : MviViewModel<ChampionDetailSingleEvent, Champion, Versions, ChampionDetailIntent>() {

    override fun processIntent(intent: ChampionDetailIntent): Flow<ViewState<Champion, Versions>> {
        return when(intent) {
            is ChampionDetailIntent.GetChampion -> {
                getChampionsUseCase.execute(GetChampionsUseCase.Parameters(intent.version, intent.language))
                    .map {
                        ViewState<Champion, Versions>(dataList = it.filter { champion ->
                            champion.id == intent.championId
                        }, isLoading = false)
                    }.catch { e ->
                        emit(ViewState(error = e))
                    }
            }
        }
    }
}

sealed class ChampionDetailIntent: MviIntent {
    data class GetChampion(val version: String, val language: String, val championId: String) : ChampionDetailIntent()
}

sealed class ChampionDetailSingleEvent: MviSingleEvent {

}