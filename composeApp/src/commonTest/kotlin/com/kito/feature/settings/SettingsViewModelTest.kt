package com.kito.feature.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.kito.core.datastore.PrefsRepository
import com.kito.core.platform.SecureStorage
import com.kito.core.presentation.components.state.SyncUiState
import com.kito.feature.schedule.notification.NotificationController
import com.kito.feature.settings.presentation.SettingsViewModel
import com.kito.feature.settings.presentation.SettingsEvent
import com.kito.testing.FakeAttendanceRepository
import com.kito.testing.FakeAuthRepository
import com.kito.testing.FakeSyncUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val tempPath = "settings_prefs_test.preferences_pb".toPath()
    private lateinit var prefsRepository: PrefsRepository
    private lateinit var datastoreScope: CoroutineScope

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        datastoreScope = CoroutineScope(testDispatcher + SupervisorJob())
        prefsRepository = PrefsRepository(
            PreferenceDataStoreFactory.createWithPath(
                scope = datastoreScope,
                produceFile = { tempPath }
            )
        )
    }

    @AfterTest
    fun teardown() {
        datastoreScope.cancel()
        Dispatchers.resetMain()
        try {
            FileSystem.SYSTEM.delete(tempPath)
        } catch (_: Exception) {
            // ignore
        }
    }

    private fun vm() = SettingsViewModel(
        prefs = prefsRepository,
        secureStorage = SecureStorage(),
        attendanceRepository = FakeAttendanceRepository(),
        appSyncUseCase = FakeSyncUseCase(),
        notificationController = object : NotificationController { override suspend fun sync() {} },
        authRepository = FakeAuthRepository(),
        dispatcher = testDispatcher,
    )

    @Test
    fun syncState_initiallyIdle() = runTest(testDispatcher) {
        assertIs<SyncUiState.Idle>(vm().syncState.value)
    }

    @Test
    fun setSyncStateIdle_resetsToIdle() = runTest(testDispatcher) {
        val v = vm()
        v.onEvent(SettingsEvent.SyncStateIdle)
        assertIs<SyncUiState.Idle>(v.syncState.value)
    }
}
