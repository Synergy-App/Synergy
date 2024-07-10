package com.sungkyul.synergy.training_space.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeWebViewBinding

class PracticeWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
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
        val learningInfo = intent.getStringExtra("LEARNING_INFO")
        val learningInfoTextView = findViewById<TextView>(R.id.learning_info_tv)
        learningInfoTextView.text = learningInfo

        // Include된 레이아웃에서 버튼 찾기
        val includeLayout = findViewById<RelativeLayout>(R.id.practice_nav_layout)

        val button1 = includeLayout.findViewById<ImageView>(R.id.home_nav)
        val button2 = includeLayout.findViewById<ImageView>(R.id.back_nav)
        val button3 = includeLayout.findViewById<ImageView>(R.id.next_nav)

        button1.setOnClickListener {
            val intent= Intent(this,PracticeResultActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            // 이전 문제로 이동하는 로직 추가
            val problemNumber = intent.getIntExtra("PROBLEM_NUMBER", 1)
            val previousProblemNumber = problemNumber - 1
            startWebViewActivity(getUrlForProblem(previousProblemNumber), getLearningInfoForProblem(previousProblemNumber), previousProblemNumber)
        }

        button3.setOnClickListener {
            // 다음 문제로 이동하는 로직 추가
            val problemNumber = intent.getIntExtra("PROBLEM_NUMBER", 1)
            val nextProblemNumber = problemNumber + 1
            startWebViewActivity(getUrlForProblem(nextProblemNumber), getLearningInfoForProblem(nextProblemNumber), nextProblemNumber)
        }
    }

    // 문제 번호에 따라 다음 문제의 URL 반환
    private fun getUrlForProblem(problemNumber: Int): String {
        return when (problemNumber) {
            1 -> "https://youtu.be/DSkR2nc3uUE"
            2 -> "https://youtu.be/aRTg0jgNQvo"
            3 -> "https://youtu.be/rLWAD9LW4aY"
            4 -> "https://youtube.com/shorts/t5foNMcSMn0"
            5 -> "https://youtu.be/UVARovWqK-M"
            else -> "https://example.com"
        }
    }

    // 문제 번호에 따라 다음 문제의 학습 정보 반환
    private fun getLearningInfoForProblem(problemNumber: Int): String {
        return when (problemNumber) {
            1 -> "잠금화면에서 홈화면으로 이동하세요."
            2 -> "상단바를 내리세요."
            3 -> "화면 밝기를 올리세요."
            4 -> "플레이 스토어를 홈 화면으로 이동하시오."
            5 -> "최근 사용한 앱 목록을 확인하세요."
            else -> "학습 정보를 찾을 수 없습니다."
        }
    }

    private fun startWebViewActivity(url: String, learningInfo: String, problemNumber: Int) {
        val intent = Intent(this@PracticeWebViewActivity, PracticeWebViewActivity::class.java)
        intent.putExtra("WEB_URL", url)
        intent.putExtra("LEARNING_INFO", learningInfo)
        intent.putExtra("PROBLEM_NUMBER", problemNumber)
        startActivity(intent)
    }
}