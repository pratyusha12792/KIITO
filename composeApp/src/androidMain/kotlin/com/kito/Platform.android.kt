package com.kito

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.RELEASE}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

private val darkTextRequesters = androidx.compose.runtime.mutableStateOf(0)

@androidx.compose.runtime.Composable
actual fun SetSystemBarAppearance(isLightForeground: Boolean) {
    val view = androidx.compose.ui.platform.LocalView.current
    if (!view.isInEditMode) {
        androidx.compose.runtime.DisposableEffect(isLightForeground) {
            if (!isLightForeground) {
                darkTextRequesters.value++
            }
            onDispose {
                if (!isLightForeground) {
                    darkTextRequesters.value--
                }
            }
        }
        
        val forceDarkText = darkTextRequesters.value > 0
        androidx.compose.runtime.LaunchedEffect(forceDarkText) {
            val window = (view.context as android.app.Activity).window
            androidx.core.view.WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = forceDarkText
        }
    }
}
