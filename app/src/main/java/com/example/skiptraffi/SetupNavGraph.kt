package com.example.skiptraffi

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.skiptraffi.ui.DetailScreen
import com.example.skiptraffi.ui.TrafficMessageViewModel
import com.example.skiptraffi.ui.TrafficScreen
import com.example.skiptraffi.ui.TrafficViewModel
import com.example.skiptraffi.util.Constants.DETAIL_CITY_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TrafficArea.route) {
        composable(route = Screen.TrafficArea.route) {
            val viewModel = viewModel<TrafficViewModel>()
            TrafficScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument(DETAIL_CITY_KEY) {
            type = NavType.StringType
        })) {
            val viewModel = viewModel<TrafficMessageViewModel>()
            DetailScreen(navController = navController, viewModel = viewModel, it.arguments?.getString(DETAIL_CITY_KEY))
        }
    }
}