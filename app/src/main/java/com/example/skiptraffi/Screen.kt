package com.example.skiptraffi

sealed class Screen(val route: String) {
    object TrafficArea : Screen(route = "home_screen")
    object Detail : Screen(route = "detail_screen/{cityName}") {
        fun passCity(cityName: String): String {
            return "detail_screen/$cityName"
        }
    }
}