package com.kito.core.presentation.components

import android.graphics.Bitmap
import android.graphics.Color
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay

@Composable
actual fun PromotionWebView(
    url: String,
    modifier: Modifier,
    onLoadingStateChange: (Boolean) -> Unit
) {
    val context = LocalContext.current

    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            setBackgroundColor(Color.TRANSPARENT)

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    onLoadingStateChange(true)
                }

                override fun onPageFinished(
                    view: WebView?,
                    url: String?
                ) {
                    onLoadingStateChange(false)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: android.webkit.WebResourceRequest?
                ): Boolean {

                    val clickedUrl = request?.url.toString()

                    return if (
                        clickedUrl.startsWith("geo:") ||
                        clickedUrl.contains("google.com/maps") ||
                        clickedUrl.startsWith("intent:")
                    ) {

                        try {
                            val intent = android.content.Intent(
                                android.content.Intent.ACTION_VIEW,
                                android.net.Uri.parse(clickedUrl)
                            )
                            view?.context?.startActivity(intent)
                        } catch (e: Exception) {
                            view?.loadUrl(clickedUrl)
                        }

                        true // We handled it
                    } else {
                        false // Let WebView load normally
                    }
                }
            }
        }
    }

    LaunchedEffect(url) {
        delay(650)
        webView.loadUrl(url)
    }

    AndroidView(
        factory = { webView },
        modifier = modifier
    )
}