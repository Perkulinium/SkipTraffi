package com.example.skiptraffi.util

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class AppState(
    val navController: NavHostController,
    val context: Context,
    val title: MutableState<String>,
    val hasBackButton: MutableState<Boolean>,
    val hasEndButton: MutableState<Boolean>,
    val hasToolbar: MutableState<Boolean>,
    val bottomBarHeight: MutableState<Dp>,
    var onEndButtonClicked: () -> Unit = {}
) {
    fun setToolbarState(
        title: String = "",
        hasBackButton: Boolean = true,
        hasEndButton: Boolean = false,
        hasToolbar: Boolean = true,
        onEndButtonClicked: (() -> Unit)? = null
    ) {
        this.title.value = title
        this.hasBackButton.value = hasBackButton
        this.hasEndButton.value = hasEndButton
        this.hasToolbar.value = hasToolbar
        if (onEndButtonClicked != null) {
            this.onEndButtonClicked = onEndButtonClicked
        }
    }

    fun setBottomPadding(bottomPadding: Dp) {
        if (bottomPadding > 0.dp && bottomPadding != this.bottomBarHeight.value) {
            this.bottomBarHeight.value = bottomPadding
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberAnimatedNavController(),
    context: Context = LocalContext.current,
    title: String = "",
    hasBackButton: Boolean = false,
    hasEndButton: Boolean = false,
    hasToolbar: Boolean = false,
    bottomBarHeight: Dp = 0.dp
) = remember(
    navController,
    context,
    title,
    hasBackButton,
    hasEndButton,
    hasToolbar,
    bottomBarHeight
) {
    AppState(
        navController,
        context,
        mutableStateOf(title),
        mutableStateOf(hasBackButton),
        mutableStateOf(hasEndButton),
        mutableStateOf(hasToolbar),
        mutableStateOf(bottomBarHeight)
    )
}