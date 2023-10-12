package com.hu.loldex.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
@JsonClass(generateAdapter = true)
data class ChampionsEntity(
    @Json(name = "type") val type: String,
    @Json(name = "format") val format: String,
    @Json(name = "version") val version: String,
    @Json(name = "data") val data: Map<String, ChampionEntity>
)