package com.hu.loldex.ui.main

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hu.loldex.model.Champion
import com.hu.loldex.ui.utils.ChampionImage

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailRoute(
    vm: ChampionDetailViewModel,
    version: String,
    language: String,
    championId: String,
    navController: NavController,
    finishActivity: () -> Unit = { }
) {
    val champion by vm.champion.collectAsState()

    LaunchedEffect(Unit) {
        vm.getChampionDetail(version, language, championId)
    }

    champion?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        androidx.compose.material3.Text("LoLDex")
                    }
                )
            }
        ) { innerPadding ->
            ChampionDetailScreen(
                innerPadding,
                champion = it,
                onBackPressed = finishActivity
            )
        }
    }
}

@Composable
fun ChampionDetailScreen(
    innerPadding: PaddingValues,
    champion: Champion,
    onBackPressed: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .padding(horizontal = 11.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = spacedBy(16.dp)
    ) {
        Text(
            text = champion.name,
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        ChampionImage(
            url = champion.getChampionLoadingImageUrl(),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = champion.title,
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = champion.blurb,
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = champion.tags.toString(),
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = champion.info.toString(),
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = champion.stats.toString(),
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }

}