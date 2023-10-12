package com.hu.loldex.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun ChampionListRoute(
    vm: ChampionListViewModel,
    navController: NavController,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("LoLDex")
                }
            )
        }
    ) { innerPadding ->
        ChampionListScreen(innerPadding, vm, navController, onBackPressed)
    }

}

@Composable
private fun ChampionListScreen(
    innerPadding : PaddingValues,
    vm: ChampionListViewModel,
    navController: NavController,
    onBackPressed: () -> Unit,
) {

    val version by vm.version.collectAsState()
    val champions by vm.champions.collectAsState()
    val language = vm.language

    var selectedVersion = version.firstOrNull()
    var selectedLanguage = language.first()


    Column(modifier = Modifier.padding(innerPadding)) {
        Row {
            DropdownMenuOptions(
                label = "Version",
                options = version,
                onClick = {
                    selectedVersion = it
                    vm.getChampions(selectedVersion ?: "", selectedLanguage)
                }
            )
            DropdownMenuOptions(
                label = "Language",
                options = language,
                onClick = {
                    selectedLanguage = it
                    vm.getChampions(selectedVersion ?: "", selectedLanguage)
                }
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = champions, key = { it.key }) { champion ->
                ChampionItem(navController, champion, selectedLanguage)
            }
        }
    }
}

@Composable
private fun ChampionItem(navController: NavController, champion: Champion, language: String) {
    Column(
        Modifier
            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("${MainDestinations.CHAMPION_DETAIL_ROUTE}/${champion.version}/$language/${champion.id}")
            }
    ) {
        ChampionImage(
            url = champion.image.getChampionImageUrl(champion.version),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = champion.name,
            fontSize = 13.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownMenuOptions(
    label: String,
    options: List<String>,
    onClick: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options.getOrNull(0)) }

    LaunchedEffect(options) {
        selectedOptionText = options.getOrNull(0)
    }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .padding(bottom = 10.dp)
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText ?: "",
            onValueChange = {

            },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier.menuAnchor(),
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        onClick(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}