package com.mitv.pro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var splashImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        splashImage = findViewById(R.id.splashImage)

        // 1. Splash Screen Animation (Left to Right Swipe)
        val swipeAnim = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        swipeAnim.duration = 1000
        splashImage.startAnimation(swipeAnim)

        // 2. WebView Settings
        setupWebView()

        // 3. 3 Seconds baad WebView load karna
        Handler(Looper.getMainLooper()).postDelayed({
            splashImage.visibility = View.GONE
            webView.visibility = View.VISIBLE
            webView.loadUrl("https://mitvnetworks.netlify.app")
        }, 3000)
    }

    private fun setupWebView() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                // VLC aur MX Player support
                if (url.startsWith("vlc://") || url.contains("intent://")) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        return true
                    } catch (e: Exception) { return false }
                }
                return false
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}
            webView.loadUrl("https://mitvnetworks.netlify.app")
        }, 3000)
    }

    private fun setupWebView() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                // External players like VLC/MX Player handling
                if (url.startsWith("vlc://") || url.contains("intent://")) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        return true
                    } catch (e: Exception) { return false }
                }
                return false
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}
