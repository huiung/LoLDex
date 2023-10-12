package com.hu.loldex.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.loldex.domain.GetChampionsUseCase
import com.hu.loldex.domain.GetVersionUseCase
import com.hu.loldex.model.Champion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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
) : ViewModel() {

    private val _champion: MutableStateFlow<Champion?> = MutableStateFlow(null)
    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)

    val champion: StateFlow<Champion?> = _champion
    val error = _error

    fun getChampionDetail(version: String, language: String, championId: String) {
        viewModelScope.launch {
            getChampionsUseCase.execute(GetChampionsUseCase.Parameters(version, language, false)).first()
                .onSuccess {
                    _champion.value = it.find { champion ->
                        champion.id == championId
                    }
                }.onFailure {
                    _error.value = it.message
                }
        }
    }
}