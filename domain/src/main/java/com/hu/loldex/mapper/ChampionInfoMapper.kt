package com.hu.loldex.mapper

import com.hu.loldex.data.api.model.ChampionEntity
import com.hu.loldex.model.Champion
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
class ChampionInfoMapper @Inject constructor(

) : EntityMapper<ChampionEntity.Info, Champion.Info> {
    override fun mapFromEntity(entity: ChampionEntity.Info): Champion.Info {
        return Champion.Info(
            attack = entity.attack,
            defense = entity.defense,
            magic = entity.magic,
            difficulty = entity.difficulty
        )
    }

    override fun mapToEntity(model: Champion.Info): ChampionEntity.Info {
        return ChampionEntity.Info(
            attack = model.attack,
            defense = model.defense,
            magic = model.magic,
            difficulty = model.difficulty
        )
    }

}