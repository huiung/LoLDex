package com.hu.loldex.domain

import com.hu.loldex.model.Champion
import com.hu.loldex.mapper.ChampionMapper
import com.hu.loldex.data.repository.LoLDexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
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
class GetChampionsUseCase @Inject constructor(
    private val repository: LoLDexRepository,
    private val championMapper: ChampionMapper
) : UseCase<GetChampionsUseCase.Parameters, List<Champion>>() {
    override fun execute(parameters: GetChampionsUseCase.Parameters): Flow<Result<List<Champion>>> =
        repository.getChampions(parameters.version, parameters.language, parameters.forceLoad)
            .map { list ->
                Result.success(
                    list.map { championEntity ->
                        championMapper.mapFromEntity(championEntity)
                    }
                )
            }.catch { e ->
                emit(Result.failure(e))
            }

    data class Parameters(
        val version: String,
        val language: String,
        val forceLoad: Boolean = true
    )
}