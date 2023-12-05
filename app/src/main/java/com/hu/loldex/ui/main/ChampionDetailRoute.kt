package com.hu.loldex.ui.main

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hu.loldex.R
import com.hu.loldex.model.Champion
import com.hu.loldex.ui.theme.Blue300
import com.hu.loldex.ui.theme.Blue500
import com.hu.loldex.ui.theme.Green300
import com.hu.loldex.ui.theme.Green500
import com.hu.loldex.ui.theme.Red300
import com.hu.loldex.ui.theme.Red500
import com.hu.loldex.ui.theme.Yellow300
import com.hu.loldex.ui.theme.Yellow500
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
    navController: NavController,
    onBackPressed: () -> Unit = { }
) {
    val viewState by vm.viewState.collectAsState()
    val champion = viewState.champion

    champion?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 16.dp)
                                .clickable {
                                    onBackPressed()
                                }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    }
                )
            }
        ) { innerPadding ->
            ChampionDetailScreen(
                innerPadding,
                champion = it,
                vm = vm,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun ChampionDetailScreen(
    innerPadding: PaddingValues,
    champion: Champion,
    vm: ChampionDetailViewModel,
    onBackPressed: () -> Unit = { }
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 11.dp)
            .verticalScroll(scrollState),
        verticalArrangement = spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = champion.name + ", " + champion.title,
            fontSize = 17.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        ChampionImage(
            url = champion.getChampionSplashImageUrl(),
            modifier = Modifier
                .padding(horizontal = 11.dp)
                .clip(RoundedCornerShape(25.dp))
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        DescriptionItem("역할군", champion.tags.toString())
        DescriptionItem("소개", champion.blurb)
        DescriptionItem("정보", champion.info.toString())

        Column(modifier = Modifier.padding(horizontal = 11.dp)) {
            Text(
                text = "능력치",
                fontSize = 17.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.border(1.dp, Color.Black),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TableCell(text = "구분", weight = 1f)
                TableCell(
                    text = "1레벨 능력치\n(+레벨당 상승)",
                    weight = 1f,
                    modifier = Modifier.border(1.dp, Color.Black)
                )
                TableCell(text = "18레벨 능력치", weight = 1f)
            }
            TableRow("hp", champion.stats.hp, champion.stats.hpperlevel)
            TableRow("mp", champion.stats.mp, champion.stats.mpperlevel)
            TableRow("movespeed", champion.stats.movespeed, 0.0)
            TableRow("armor", champion.stats.armor, champion.stats.armorperlevel)
            TableRow("spellblock", champion.stats.spellblock, champion.stats.spellblockperlevel)
            TableRow("attackrange", champion.stats.attackrange, 0.0)
            TableRow("hpregen", champion.stats.hpregen, champion.stats.hpregenperlevel)
            TableRow("mpregen", champion.stats.mpregen, champion.stats.mpregenperlevel)
            TableRow("crit", champion.stats.crit, champion.stats.critperlevel)
            TableRow("attackdamage", champion.stats.attackdamage, champion.stats.attackdamageperlevel)
            TableRow("attackspeed", champion.stats.attackspeed, champion.stats.attackspeedperlevel)
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun DescriptionItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 11.dp),
        verticalArrangement = spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = description,
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun TableRow(
    stateText: String,
    stats: Double,
    statsPerLevel: Double,
) {
    Row(
        modifier = Modifier.border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TableCell(text = stateText, weight = 1f)
        TableCell(
            text = if (statsPerLevel != 0.0) "${stats}(+${statsPerLevel})" else "${stats}",
            weight = 1f,
            modifier = Modifier.border(1.dp, Color.Black)
        )
        TableCell(
            text = "${stats + statsPerLevel * 17}",
            weight = 1f
        )
    }
}
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = modifier
            .weight(weight)
            .padding(8.dp)
    )
}