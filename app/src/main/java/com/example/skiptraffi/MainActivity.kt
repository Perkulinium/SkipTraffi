package com.example.skiptraffi

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skiptraffi.Route.ROOT_ROUTE
import com.example.skiptraffi.ui.components.SkipTraffiBottomNavigation
import com.example.skiptraffi.ui.components.SkipTraffiTopBar
import com.example.skiptraffi.ui.theme.SkipTraffiTheme
import com.example.skiptraffi.util.AppState
import com.example.skiptraffi.util.Constants.LATITUDE_KEY
import com.example.skiptraffi.util.Constants.LONGITUDE_KEY
import com.example.skiptraffi.util.rememberAppState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.Route

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                LONGITUDE_KEY = location?.longitude ?: 0.0
                LATITUDE_KEY = location?.latitude ?: 0.0
            }

        setContent {
            SkipTraffiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    topAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun topAppBar() {
    val appState: AppState = rememberAppState()
    Scaffold(
        topBar = {
            SkipTraffiTopBar(
                title = appState.title.value,
                hasBackButton = appState.hasBackButton.value,
                hasEndButton = appState.hasEndButton.value,
                isVisible = appState.hasToolbar.value,
                onBackPressed = { appState.navController.navigateUp() },
                onEndButtonPressed = { appState.onEndButtonClicked.invoke() })
        },
        bottomBar = { SkipTraffiBottomNavigation(navController = appState.navController) }) {
        appState.setBottomPadding(it.calculateBottomPadding())
        AnimatedNavHost(
            navController = appState.navController,
            startDestination = com.example.skiptraffi.Route.HOME_SCREEN,
            route = ROOT_ROUTE
        ) {
            navGraph(appState)
        }
    }
}


