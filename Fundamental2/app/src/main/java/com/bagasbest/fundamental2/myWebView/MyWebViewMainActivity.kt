package com.bagasbest.fundamental2.myWebView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.bagasbest.fundamental2.databinding.ActivityMyWebViewMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyWidgetMainBinding

class MyWebViewMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyWebViewMainBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWebViewMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(applicationContext, "Web Dicoding berhasil dimuat", Toast.LENGTH_LONG).show()
            }
        }
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.loadUrl("https://www.dicoding.com")
    }
}