package com.hu.loldex.data.mapper

import com.hu.loldex.data.api.model.ChampionApiData
import com.hu.loldex.data.entity.ChampionEntity
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
class ChampionStatsEntityMapper @Inject constructor(

) : EntityMapper<ChampionEntity.Stats, ChampionApiData.Stats> {
    override fun mapFromEntity(entity: ChampionEntity.Stats): ChampionApiData.Stats {
        return ChampionApiData.Stats(
            hp = entity.hp,
            hpperlevel = entity.hpperlevel,
            mp = entity.mp,
            mpperlevel = entity.mpperlevel,
            movespeed = entity.movespeed,
            armor = entity.armor,
            armorperlevel = entity.armorperlevel,
            spellblock = entity.spellblock,
            spellblockperlevel = entity.spellblockperlevel,
            attackrange = entity.attackrange,
            hpregen = entity.hpregen,
            hpregenperlevel = entity.hpregenperlevel,
            mpregen = entity.mpregen,
            mpregenperlevel = entity.mpregenperlevel,
            crit = entity.crit,
            critperlevel = entity.critperlevel,
            attackdamage = entity.attackdamage,
            attackdamageperlevel = entity.attackdamageperlevel,
            attackspeedperlevel = entity.attackspeedperlevel,
            attackspeed = entity.attackspeed
        )
    }

    override fun mapToEntity(model: ChampionApiData.Stats): ChampionEntity.Stats {
        return ChampionEntity.Stats(
            hp = model.hp,
            hpperlevel = model.hpperlevel,
            mp = model.mp,
            mpperlevel = model.mpperlevel,
            movespeed = model.movespeed,
            armor = model.armor,
            armorperlevel = model.armorperlevel,
            spellblock = model.spellblock,
            spellblockperlevel = model.spellblockperlevel,
            attackrange = model.attackrange,
            hpregen = model.hpregen,
            hpregenperlevel = model.hpregenperlevel,
            mpregen = model.mpregen,
            mpregenperlevel = model.mpregenperlevel,
            crit = model.crit,
            critperlevel = model.critperlevel,
            attackdamage = model.attackdamage,
            attackdamageperlevel = model.attackdamageperlevel,
            attackspeedperlevel = model.attackspeedperlevel,
            attackspeed = model.attackspeed
        )
    }
}