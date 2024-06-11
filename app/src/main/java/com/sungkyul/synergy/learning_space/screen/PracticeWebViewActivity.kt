package com.sungkyul.synergy.learning_space.screen

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeWebViewBinding

class PracticeWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        // Intent에서 전달된 URL 가져오기
        val url = intent.getStringExtra("WEB_URL")
        // URL이 null이 아닌 경우 WebView로 로드
        url?.let {
            binding.webView.loadUrl(it)
        }
        val learningInfo = intent.getSerializableExtra("LEARNING_INFO")
        val learningInfoTextView = findViewById<TextView>(R.id.learning_info_tv)
        learningInfoTextView.text = learningInfo.toString()

        // Include된 레이아웃에서 버튼 찾기
        val includeLayout = findViewById<RelativeLayout>(R.id.practice_nav_layout)

        val button1 = includeLayout.findViewById<ImageView>(R.id.home_nav)
        val button2 = includeLayout.findViewById<ImageView>(R.id.back_nav)
        val button3 = includeLayout.findViewById<ImageView>(R.id.next_nav)

        button1.setOnClickListener {
            val intent = Intent(this@PracticeWebViewActivity, MainActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            // val intent = Intent(this@PracticeWebViewActivity, MainActivity::class.java)
            // startActivity(intent)
        }

        button3.setOnClickListener {
            // val intent = Intent(this@PracticeWebViewActivity, NewActivity3::class.java)
            // startActivity(intent)
        }
    }
}
