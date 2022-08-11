package com.example.skiptraffi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.skiptraffi.ui.*
import com.example.skiptraffi.util.Animation
import com.example.skiptraffi.util.AppState
import com.example.skiptraffi.util.Constants.DETAIL_CITY_KEY
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.navGraph(appState: AppState) {

    navigation(
        route = Route.HOME_SCREEN,
        startDestination = Screen.TrafficArea.route,
        enterTransition = { Animation.navigationSlideEnter },
        exitTransition = { Animation.navigationFadeExit },
        popEnterTransition = { Animation.navigationFadeEnter },
        popExitTransition = { Animation.navigationFadeExit }
    ) {
        composable(route = Screen.TrafficArea.route) {
            val viewModel = viewModel<TrafficViewModel>()
            TrafficScreen(
                viewModel = viewModel,
                navController = appState.navController,
                appState = appState
            )
        }

        composable(route = Screen.Detail.route, arguments = listOf(navArgument(DETAIL_CITY_KEY) {
            type = NavType.StringType
        })) {
            val viewModel = viewModel<TrafficMessageViewModel>()
            DetailScreen(
                navController = appState.navController,
                viewModel = viewModel,
                it.arguments?.getString(DETAIL_CITY_KEY),
                appState = appState
            )
        }

        composable(route = Screen.GoogleMaps.route)  {
            val viewModel = viewModel<GoogleMapsViewModel>()
            GoogleMapsScreen(
                navController = appState.navController,
                viewModel = viewModel,
                it.arguments?.getString(DETAIL_CITY_KEY),
                appState = appState
            )
        }

        composable(route = Screen.CurrentPosition.route)  {
            val viewModel = viewModel<CurrentPositionViewModel>()
            CurrentPositionScreen(
                navController = appState.navController,
                viewModel = viewModel,
                it.arguments?.getString(DETAIL_CITY_KEY),
                appState = appState
            )
        }
    }
}
