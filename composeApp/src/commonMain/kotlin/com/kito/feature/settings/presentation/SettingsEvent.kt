package com.kito.feature.settings.presentation

sealed interface SettingsEvent {
    data object RequestEnableNotifications : SettingsEvent
    data object ClearPendingNotificationEnable : SettingsEvent
    data object RetryPendingNotificationEnable : SettingsEvent
    data object SyncStateIdle : SettingsEvent
    data class ChangeName(val name: String) : SettingsEvent
    data class ChangeRoll(val roll: String) : SettingsEvent
    data class ChangeAttendance(val attendance: Int) : SettingsEvent
    data class ChangeYearTerm(val year: String, val term: String) : SettingsEvent
    data object LogOut : SettingsEvent
    data class LogIn(val password: String) : SettingsEvent
    data class SetNotificationState(val state: Boolean) : SettingsEvent
}
