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
class ChampionListViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getVersionUseCase: GetVersionUseCase
) : ViewModel() {

    private val _version : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _champions : MutableStateFlow<List<Champion>> = MutableStateFlow(emptyList())
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)

    val version : StateFlow<List<String>> = _version
    val champions : StateFlow<List<Champion>> = _champions
    val language : List<String> = listOf("en_US", "ko_KR")
    val error = _error
    init {
        viewModelScope.launch {
            getVersionUseCase.execute(Unit).first()
                .onSuccess {
                    _version.value = it
                    getChampions(it.first(), language.first())
                }.onFailure {
                    _error.value = it.message
                }
        }
    }

    fun getChampions(version: String, language: String) {
        viewModelScope.launch {
            getChampionsUseCase.execute(GetChampionsUseCase.Parameters(version, language)).first()
                .onSuccess {
                    _champions.value = it
                }.onFailure {
                    _error.value = it.message
                }
        }
    }
}