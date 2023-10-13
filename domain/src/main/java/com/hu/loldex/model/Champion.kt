package com.hu.loldex.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class Champion(
    val version: String,
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    val info: Info,
    val image: Image,
    val tags: List<String>,
    val partype: String,
    val stats: Stats
) : Parcelable {

    fun getChampionLoadingImageUrl(): String {
        return "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${id}_0.jpg"
    }
    @Parcelize
    data class Info(
        val attack: Int,
        val defense: Int,
        val magic: Int,
        val difficulty: Int
    ) : Parcelable

    @Parcelize
    data class Image(
        val full: String,
        val sprite: String,
        val group: String,
        val x: Int,
        val y: Int,
        val w: Int,
        val h: Int
    ) : Parcelable {
        fun getChampionImageUrl(version: String): String {
            return "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/$full"
        }
    }

    @Parcelize
    data class Stats(
        val hp: Double,
        val hpperlevel: Double,
        val mp: Double,
        val mpperlevel: Double,
        val movespeed: Double,
        val armor: Double,
        val armorperlevel: Double,
        val spellblock: Double,
        val spellblockperlevel: Double,
        val attackrange: Double,
        val hpregen: Double,
        val hpregenperlevel: Double,
        val mpregen: Double,
        val mpregenperlevel: Double,
        val crit: Double,
        val critperlevel: Double,
        val attackdamage: Double,
        val attackdamageperlevel: Double,
        val attackspeedperlevel: Double,
        val attackspeed: Double
    ) : Parcelable
}