package com.sungkyul.synergy.learning_space.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.sungkyul.synergy.R

class PracticeResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_result)

        // SharedPreferences에서 결과를 가져옴
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
        val result4 = sharedPreferences.getBoolean("move_app_success", true)
        val result5 = sharedPreferences.getBoolean("quiz_result", true)
        val result1 = sharedPreferences.getBoolean("unlock_success", true)
        val result2 = sharedPreferences.getBoolean("scroll_success", true)
        val result3 = sharedPreferences.getBoolean("brightness_adjust_success", true)

        val option_text1 = findViewById<TextView>(R.id.option_text1)
        option_text1.text = if (result1) "1. 맞았습니다" else "1. 틀렸습니다"
        val option_text2 = findViewById<TextView>(R.id.option_text2)
        option_text2.text = if (result2) "2. 맞았습니다" else "2. 틀렸습니다"
        val option_text3 = findViewById<TextView>(R.id.option_text3)
        option_text3.text = if (result3) "3. 맞았습니다" else "3. 틀렸습니다"
        val option_text4 = findViewById<TextView>(R.id.option_text4)
        option_text4.text = if (result4) "4. 맞았습니다" else "4. 틀렸습니다"
        val option_text5 = findViewById<TextView>(R.id.option_text5)
        option_text5.text = if (result5) "5. 맞았습니다" else "5. 틀렸습니다"

        val problem1 = findViewById<ImageView>(R.id.problem1)
        problem1.setImageResource(if (result1) R.drawable.correct else R.drawable.wrong)

        val problem2 = findViewById<ImageView>(R.id.problem2)
        problem2.setImageResource(if (result2) R.drawable.correct else R.drawable.wrong)

        val problem3 = findViewById<ImageView>(R.id.problem3)
        problem3.setImageResource(if (result3) R.drawable.correct else R.drawable.wrong)

        val problem4 = findViewById<ImageView>(R.id.problem4)
        problem4.setImageResource(if (result4) R.drawable.correct else R.drawable.wrong)

        val problem5 = findViewById<ImageView>(R.id.problem5)
        problem5.setImageResource(if (result5) R.drawable.correct else R.drawable.wrong)

        // 클릭 이벤트 처리
        problem1.setOnClickListener {
            startWebViewActivity("https://www.google.com", "잠금화면에서 홈화면으로 이동하세요.")
        }

        problem2.setOnClickListener {
            startWebViewActivity("https://www.google.com", "상단바를 내리세요.")
        }

        problem3.setOnClickListener {
            startWebViewActivity("https://www.google.com", "화면 밝기를 올리세요.")
        }

        problem4.setOnClickListener {
            startWebViewActivity("https://www.google.com", "플레이 스토어를 홈 화면으로 이동하시오.")
        }

        problem5.setOnClickListener {
            startWebViewActivity("https://www.google.com", "최근 사용한 앱 목록을 확인하세요.")
        }

    }

    private fun startWebViewActivity(url: String, learningInfo: String) {
        val intent = Intent(this@PracticeResultActivity, PracticeWebViewActivity::class.java)
        intent.putExtra("WEB_URL", url)
        intent.putExtra("LEARNING_INFO", learningInfo)
        startActivity(intent)
    }

}