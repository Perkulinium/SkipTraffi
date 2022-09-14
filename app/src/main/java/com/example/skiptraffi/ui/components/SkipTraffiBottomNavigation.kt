package com.example.skiptraffi.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.skiptraffi.Screen

@Composable
fun SkipTraffiBottomNavigation(navController: NavController) {
    val items = remember {
        listOf(
            Screen.TrafficArea,
            Screen.GoogleMaps,
            Screen.CurrentPosition
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hasBottomBar = items.any { it.route == currentRoute }
    AnimatedVisibility(
        visible = hasBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    onClick = {
                        navController.navigate(item.route) {
                            if (currentRoute != null) {
                                popUpTo(currentRoute) {
                                    inclusive = true
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = { Text(text = item.title) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.secondary,
                    alwaysShowLabel = true
                )
            }
        }
    }
}