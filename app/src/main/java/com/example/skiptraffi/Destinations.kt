package com.example.skiptraffi

import com.compose.type_safe_args.annotation.ComposeDestination

object Route {
    const val ROOT_ROUTE = "root"
    const val HOME_SCREEN = "home_screen"
}

@ComposeDestination
interface TrafficAreaDestination {
    companion object
}