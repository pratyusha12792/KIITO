import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kito.core.di.initKoin
import com.kito.core.presentation.theme.KitoTheme
import com.kito.feature.app.presentation.MainUI
import org.koin.compose.KoinContext

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Kiito"
        ) {
            KitoTheme {
                MainUI()
            }
        }
    }
}
