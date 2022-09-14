package com.example.skiptraffi.util

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally

object Animation {
    val navigationSlideEnter = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(
            durationMillis = 300,
            easing = AnimationEasing.easeOutCirc
        )
    )
    val navigationFadeExit = fadeOut(animationSpec = tween(300))
    val navigationFadeEnter = fadeIn(animationSpec = tween(300))
}

object AnimationEasing {
    val easeOutCirc: Easing = CubicBezierEasing(0.0f, 0.55f, 0.45f, 1f)
    val easeOutBack: Easing = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)
}