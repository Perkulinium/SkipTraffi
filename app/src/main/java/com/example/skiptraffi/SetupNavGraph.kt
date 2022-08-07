package com.example.skiptraffi

import android.util.Log
import androidx.compose.runtime.Composable
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
            TrafficScreen(viewModel = TrafficViewModel(), navController = navController)
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument(DETAIL_CITY_KEY) {
            type = NavType.StringType
        })) {
            Log.d("TestHej", "aa: " + it.arguments?.getString(DETAIL_CITY_KEY).toString())
            DetailScreen(navController = navController, viewModel = TrafficMessageViewModel(), it.arguments?.getString(DETAIL_CITY_KEY))
        }
    }
}