package com.hu.loldex.mapper

import com.hu.loldex.data.api.model.ChampionApiData
import com.hu.loldex.entity.ChampionEntity
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
class ChampionEntityMapper @Inject constructor(
    private val championInfoEntityMapper: ChampionInfoEntityMapper,
    private val championImageEntityMapper: ChampionImageEntityMapper,
    private val championStatsEntityMapper: ChampionStatsEntityMapper
) : EntityMapper<ChampionEntity, ChampionApiData> {
    override fun mapFromEntity(entity: ChampionEntity): ChampionApiData =
        ChampionApiData(
            version = entity.version,
            id = entity.id,
            key = entity.key,
            name = entity.name,
            title = entity.title,
            blurb = entity.blurb,
            info = championInfoEntityMapper.mapFromEntity(entity.info),
            image = championImageEntityMapper.mapFromEntity(entity.image),
            tags = entity.tags,
            partype = entity.partype,
            stats = championStatsEntityMapper.mapFromEntity(entity.stats)
        )


    override fun mapToEntity(model: ChampionApiData): ChampionEntity =
        ChampionEntity(
            version = model.version,
            id = model.id,
            key = model.key,
            name = model.name,
            title = model.title,
            blurb = model.blurb,
            info = championInfoEntityMapper.mapToEntity(model.info),
            image = championImageEntityMapper.mapToEntity(model.image),
            tags = model.tags,
            partype = model.partype,
            stats = championStatsEntityMapper.mapToEntity(model.stats)
        )

}