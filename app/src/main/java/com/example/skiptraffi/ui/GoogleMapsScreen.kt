package com.example.skiptraffi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    GoogleMapsContent()
}

@Composable
fun GoogleMapsContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Upcoming feature:")
        Text(text = "Will add google maps for current location")
    }
}