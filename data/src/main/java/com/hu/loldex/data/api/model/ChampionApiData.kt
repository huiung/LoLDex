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

/*
    "Aatrox": {
      "version": "13.19.1",
      "id": "Aatrox",
      "key": "266",
      "name": "아트록스",
      "title": "다르킨의 검",
      "blurb": "한때는 공허에 맞서 싸웠던 슈리마의 명예로운 수호자 아트록스와 그의 종족은 결국 공허보다 위험한 존재가 되어 룬테라의 존속을 위협했지만, 교활한 필멸자의 마법에 속아넘어가 패배하게 되었다. 수백 년에 걸친 봉인 끝에, 아트록스는 자신의 정기가 깃든 마법 무기를 휘두르는 어리석은 자들을 타락시키고 육신을 바꾸는 것으로 다시 한번 자유의 길을 찾아내었다. 이제 이전의 잔혹한 모습을 닮은 육체를 차지한 아트록스는 세상의 종말과 오랫동안 기다려온 복수를...",
      "info": {
        "attack": 8,
        "defense": 4,
        "magic": 3,
        "difficulty": 4
      },
      "image": {
        "full": "Aatrox.png",
        "sprite": "champion0.png",
        "group": "champion",
        "x": 0,
        "y": 0,
        "w": 48,
        "h": 48
      },
      "tags": [
        "Fighter",
        "Tank"
      ],
      "partype": "피의 샘",
      "stats": {
        "hp": 650,
        "hpperlevel": 114,
        "mp": 0,
        "mpperlevel": 0,
        "movespeed": 345,
        "armor": 38,
        "armorperlevel": 4.45,
        "spellblock": 32,
        "spellblockperlevel": 2.05,
        "attackrange": 175,
        "hpregen": 3,
        "hpregenperlevel": 1,
        "mpregen": 0,
        "mpregenperlevel": 0,
        "crit": 0,
        "critperlevel": 0,
        "attackdamage": 60,
        "attackdamageperlevel": 5,
        "attackspeedperlevel": 2.5,
        "attackspeed": 0.651
      }
    },
 */
@JsonClass(generateAdapter = true)
data class ChampionApiData(
    @Json(name = "version") val version: String,
    @Json(name = "id") val id: String,
    @Json(name = "key") val key: String,
    @Json(name = "name") val name: String,
    @Json(name = "title") val title: String,
    @Json(name = "blurb") val blurb: String,
    @Json(name = "info") val info: Info,
    @Json(name = "image") val image: Image,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "partype") val partype: String,
    @Json(name = "stats") val stats: Stats
) {

    @JsonClass(generateAdapter = true)
    data class Info(
        @Json(name = "attack") val attack: Int,
        @Json(name = "defense") val defense: Int,
        @Json(name = "magic") val magic: Int,
        @Json(name = "difficulty") val difficulty: Int
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        @Json(name = "full") val full: String,
        @Json(name = "sprite") val sprite: String,
        @Json(name = "group") val group: String,
        @Json(name = "x") val x: Int,
        @Json(name = "y") val y: Int,
        @Json(name = "w") val w: Int,
        @Json(name = "h") val h: Int
    )

    @JsonClass(generateAdapter = true)
    data class Stats(
        @Json(name = "hp") val hp: Double,
        @Json(name = "hpperlevel") val hpperlevel: Double,
        @Json(name = "mp") val mp: Double,
        @Json(name = "mpperlevel") val mpperlevel: Double,
        @Json(name = "movespeed") val movespeed: Double,
        @Json(name = "armor") val armor: Double,
        @Json(name = "armorperlevel") val armorperlevel: Double,
        @Json(name = "spellblock") val spellblock: Double,
        @Json(name = "spellblockperlevel") val spellblockperlevel: Double,
        @Json(name = "attackrange") val attackrange: Double,
        @Json(name = "hpregen") val hpregen: Double,
        @Json(name = "hpregenperlevel") val hpregenperlevel: Double,
        @Json(name = "mpregen") val mpregen: Double,
        @Json(name = "mpregenperlevel") val mpregenperlevel: Double,
        @Json(name = "crit") val crit: Double,
        @Json(name = "critperlevel") val critperlevel: Double,
        @Json(name = "attackdamage") val attackdamage: Double,
        @Json(name = "attackdamageperlevel") val attackdamageperlevel: Double,
        @Json(name = "attackspeedperlevel") val attackspeedperlevel: Double,
        @Json(name = "attackspeed") val attackspeed: Double
    )
}
