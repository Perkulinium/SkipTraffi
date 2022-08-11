package com.example.skiptraffi

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.skiptraffi.ui.GoogleMapsViewModel
import com.example.skiptraffi.util.AppState

@Composable
fun GoogleMapsScreen(
    navController: NavController,
    viewModel: GoogleMapsViewModel,
    cityName: String?,
    appState: AppState
) {
    appState.setToolbarState(title = "Trafikinformation", hasBackButton = false)
}