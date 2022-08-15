package com.example.skiptraffi

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes

sealed class Screen(
    val title: String,
    @SuppressLint("SupportAnnotationUsage") @DrawableRes val icon: Int,
    val route: String
) {
    object TrafficArea : Screen(
        title = "Traffic Area",
        icon = R.drawable.baseline_home_24,
        route = "traffic_screen"
    )

    object GoogleMaps : Screen(
        title = "Google maps",
        icon = R.drawable.baseline_map_24,
        route = "google_maps_screen"
    )

    object CurrentPosition : Screen(
        title = "Current Position",
        icon = R.drawable.baseline_location_on_24,
        route = "current_position_screen"
    )

    object Detail : Screen(
        title = "",
        icon = R.drawable.baseline_map_24,
        route = "detail_screen/{cityName}"
    ) {
        fun passCity(cityName: String): String {
            return "detail_screen/$cityName"
        }
    }
}