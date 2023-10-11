package com.hu.loldex.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

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
@Composable
fun ChampionImage(
    version: String,
    postFix: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    contentDescription: String? = null
) {

    val url = getChampionImageUrl(version, postFix)
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

private fun getChampionImageUrl(version: String, postFix: String): String {
    return "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/$postFix"
}