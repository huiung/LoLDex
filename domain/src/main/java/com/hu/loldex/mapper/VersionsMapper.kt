package com.hu.loldex.mapper

import com.hu.loldex.data.entity.VersionsEntity
import com.hu.loldex.model.Versions
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
class VersionsMapper  @Inject constructor()
    : EntityMapper<VersionsEntity, Versions> {
    override fun mapFromEntity(entity: VersionsEntity): Versions {
        return Versions(
            versions = entity.versions
        )
    }

    override fun mapToEntity(model: Versions): VersionsEntity {
        return VersionsEntity(
            versions = model.versions
        )
    }
}