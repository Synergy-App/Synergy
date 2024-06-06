package com.sungkyul.synergy.learning_space.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.sungkyul.synergy.R

class PracticeWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_web_view)

        val webView: WebView = findViewById(R.id.webView)

        // JavaScript 활성화
        webView.settings.javaScriptEnabled = true

        // 웹 페이지 로드
        webView.loadUrl("https://www.youtube.com/?hl=ko&gl=KR&app=desktop")
    }
}