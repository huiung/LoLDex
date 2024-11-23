package com.hu.loldex.mapper

import com.hu.loldex.data.dto.ChampionDto
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
class ChampionImageMapper @Inject constructor(

) : DtoMapper<ChampionDto.Image, Champion.Image> {
    override fun mapFromDto(entity: ChampionDto.Image): Champion.Image {
        return Champion.Image(
            full = entity.full,
            sprite = entity.sprite,
            group = entity.group,
            x = entity.x,
            y = entity.y,
            w = entity.w,
            h = entity.h
        )
    }

    override fun mapToDto(model: Champion.Image): ChampionDto.Image {
        return ChampionDto.Image(
            full = model.full,
            sprite = model.sprite,
            group = model.group,
            x = model.x,
            y = model.y,
            w = model.w,
            h = model.h
        )
    }
}