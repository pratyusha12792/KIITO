package com.kito.feature.settings.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinInject(),
    tabNavBackStack: NavBackStack<NavKey>,
    snackbarHostState: SnackbarHostState
) {
    val name by viewModel.name.collectAsState()
    val roll by viewModel.rollNumber.collectAsState()
    val year by viewModel.year.collectAsState()
    val term by viewModel.term.collectAsState()
    val notificationState by viewModel.notificationState.collectAsState()
    val requiredAttendance by viewModel.requiredAttendance.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val syncState by viewModel.syncState.collectAsState()
    val pendingEnable by viewModel.pendingNotificationEnable.collectAsState()

    SettingsContent(
        name = name,
        roll = roll,
        year = year,
        term = term,
        notificationState = notificationState,
        requiredAttendance = requiredAttendance,
        isLoggedIn = isLoggedIn,
        syncState = syncState,
        pendingEnable = pendingEnable,
        onEvent = { viewModel.onEvent(it) },
        tabNavBackStack = tabNavBackStack,
        snackbarHostState = snackbarHostState
    )
}
