package com.sungkyul.synergy.training_space.setting.result

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.training_space.activity.ExamProblemActivity
import com.sungkyul.synergy.training_space.screen.PracticeWebViewActivity
import com.sungkyul.synergy.training_space.setting.problem.ExamSettingActivity
import com.sungkyul.synergy.utils.GalaxyButton

class ExamSettingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_message_result)
        // SharedPreferences에서 결과를 가져옴
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("PracticeSettingPrefs", Context.MODE_PRIVATE)

        val result1 = sharedPreferences.getBoolean("setting_result", true)

        val option_text1 = findViewById<TextView>(R.id.option_text1)
        option_text1.text = if (result1) "1. 맞았습니다" else "1. 틀렸습니다"

        val problem1 = findViewById<ImageView>(R.id.problem1)
        problem1.setImageResource(if (result1) R.drawable.correct else R.drawable.wrong)

        // 클릭 이벤트 처리 잠금화면
        problem1.setOnClickListener {
            startWebViewActivity("https://youtu.be/DSkR2nc3uUE", "잠금화면에서 홈화면으로 이동하세요.")
        }
        val problemBtn = findViewById<GalaxyButton>(R.id.try_again_btn)
        val backBtn = findViewById<GalaxyButton>(R.id.back_btn)

        // 돌아가기 버튼
        backBtn.setOnClickListener {
            // MainActivity로 이동하면서 SolvingFragment를 다시 표시하는 Intent 생성
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SolvingFragment")
            // MainActivity에서 이 정보를 받아 Fragment를 설정할 수 있도록 함
            startActivity(intent)
            finish() // PracticeResultActivity 종료
        }

        // 다시풀기 버튼
        problemBtn.setOnClickListener {
            val intent = Intent(this, ExamSettingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startWebViewActivity(url: String, learningInfo: String) {
        val intent = Intent(this, PracticeWebViewActivity::class.java)
        intent.putExtra("WEB_URL", url)
        intent.putExtra("LEARNING_INFO", learningInfo)
        startActivity(intent)
    }
}
