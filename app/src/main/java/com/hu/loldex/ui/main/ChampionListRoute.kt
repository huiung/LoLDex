package com.hu.loldex.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hu.loldex.entity.ChampionEntity

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
fun ChampionListRoute(
    vm: ChampionListViewModel,
    onBackPressed: () -> Unit,
) {
    ChampionListScreen(vm) {
        onBackPressed()
    }
}

@Composable
private fun ChampionListScreen(
    vm: ChampionListViewModel,
    onBackPressed: () -> Unit,
) {

    val version by vm.version.collectAsState()
    val champions by vm.champions.collectAsState()

    LazyColumn() {
//        item {
//            ChampionListHeader()
//        }
        items(champions) { champion ->
            ChampionItem(champion)
        }
    }
 }

@Composable
private fun ChampionItem(championEntity: ChampionEntity) {
    Text(text = championEntity.name)
}