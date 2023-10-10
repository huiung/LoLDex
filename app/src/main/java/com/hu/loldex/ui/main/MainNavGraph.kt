package com.hu.loldex.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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

private object MainDestinations {
    const val CHAMPION_LIST_ROUTE = "champion_list"
}
@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    finishActivity: () -> Unit = { }
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.CHAMPION_LIST_ROUTE
    ) {
        composable(route = MainDestinations.CHAMPION_LIST_ROUTE) { backStackEntry ->
            // Creates a ViewModel from the current BackStackEntry
            // Available in the androidx.hilt:hilt-navigation-compose artifact
            val viewModel = hiltViewModel<ChampionListViewModel>()
            ChampionListRoute(viewModel) {
                if (!navController.popBackStack()) {
                    finishActivity()
                }
            }
        }

    }
}