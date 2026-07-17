package com.kito.feature.app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import com.kito.core.platform.IosNavBridge

@Composable
actual fun IosBottomBarBridge(
    selectedTabIndex: Int,
    visible: Boolean,
    onTabSelected: (Int) -> Unit
) {
    val latestOnTabSelected = rememberUpdatedState(onTabSelected)

    DisposableEffect(Unit) {
        IosNavBridge.onTabSelected = { index -> latestOnTabSelected.value(index) }
        onDispose { IosNavBridge.onTabSelected = null }
    }

    LaunchedEffect(selectedTabIndex, visible) {
        IosNavBridge.publishState(selectedTabIndex, visible)
    }
}
