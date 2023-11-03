package com.hu.loldex.data.repository

import com.hu.loldex.data.entity.ChampionDao
import com.hu.loldex.data.entity.VersionsEntity
import com.hu.loldex.data.service.LoLDexService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

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
@Singleton
internal class LoLDexRepositoryImpl @Inject constructor(
    private val loLDexService: LoLDexService,
    private val championDao: ChampionDao
) : LoLDexRepository {

    override fun getVersions() = flow {
        val response = loLDexService.getVersions()
        emit(VersionsEntity(response.take(300)))
    }.flowOn(Dispatchers.IO)

    override fun getChampions(version: String, language: String, forceLoad: Boolean) = flow {
        if (!forceLoad) {
            val champions = championDao.getAll()
            champions.isNullOrEmpty().not().let {
                emit(champions)
                return@flow
            }
        }


        val response = loLDexService.getChampions(version, language)
        val dataList = response.data.values.toList()
        championDao.insertAll(dataList)
        emit(dataList)
    }.flowOn(Dispatchers.IO)
}