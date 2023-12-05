package com.hu.loldex.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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

object MainDestinations {
    const val CHAMPION_LIST_ROUTE = "champion_list"
    const val CHAMPION_DETAIL_ROUTE = "champion_detail"
}

object MainArgs {
    const val CHAMPION_ENTITY = "championEntity"
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
            val vm = hiltViewModel<ChampionListViewModel>()
            ChampionListRoute(vm, navController) {
                if (!navController.popBackStack()) {
                    finishActivity()
                }
            }
        }

        composable(
            route = "${MainDestinations.CHAMPION_DETAIL_ROUTE}/{version}/{language}/{championId}",
            arguments = listOf(
                navArgument("version") {
                    // Make argument type safe
                    type = NavType.StringType
                },
                navArgument("language") {
                    // Make argument type safe
                    type = NavType.StringType
                },
                navArgument("championId") {
                    // Make argument type safe
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val vm = hiltViewModel<ChampionDetailViewModel>()
            ChampionDetailRoute(vm, navController) {
                if (!navController.popBackStack()) {
                    finishActivity()
                }
            }

        }

    }
}