package com.example.skiptraffi.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SkipTraffiTopBar(
    title: String,
    hasBackButton: Boolean,
    hasEndButton: Boolean,
    isVisible: Boolean,
    onBackPressed: () -> Unit,
    onEndButtonPressed: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        exit = shrinkVertically() + fadeOut(),
        enter = fadeIn() + expandVertically()
    ) {
        TopAppBar(elevation = 0.dp) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (hasBackButton) {
                        IconButton(
                            onClick = onBackPressed,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    if (hasEndButton) {
                        IconButton(
                            onClick = { onEndButtonPressed.invoke() },
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}