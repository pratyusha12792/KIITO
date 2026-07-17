package com.kito.feature.app.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun IosBottomBarBridge(
    selectedTabIndex: Int,
    visible: Boolean,
    onTabSelected: (Int) -> Unit
)
