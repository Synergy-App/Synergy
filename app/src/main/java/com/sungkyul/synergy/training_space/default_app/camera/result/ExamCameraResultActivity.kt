package com.sungkyul.synergy.training_space.default_app.camera.result

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.training_space.activity.ExamProblemActivity
import com.sungkyul.synergy.training_space.default_app.camera.problem.ExamCameraProblemActivity
import com.sungkyul.synergy.training_space.screen.PracticeCheckExamActivity
import com.sungkyul.synergy.training_space.screen.PracticeWebViewActivity
import com.sungkyul.synergy.utils.GalaxyButton


class ExamCameraResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_camera_result)

        // SharedPreferences에서 결과를 가져옴
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("PracticeCameraPrefs", Context.MODE_PRIVATE)
        val result1 = sharedPreferences.getBoolean("camera_quiz_result", true)
        val result2 = sharedPreferences.getBoolean("apple_result", true)



        val option_text1 = findViewById<TextView>(R.id.option_text1)
        option_text1.text = if (result1) "1. 맞았습니다" else "1. 틀렸습니다"
        val option_text2 = findViewById<TextView>(R.id.option_text2)
        option_text2.text = if (result2) "2. 맞았습니다" else "2. 틀렸습니다"
//        val option_text3 = findViewById<TextView>(R.id.option_text3)
//        option_text3.text = if (result3) "3. 맞았습니다" else "3. 틀렸습니다"
//        val option_text4 = findViewById<TextView>(R.id.option_text4)
//        option_text4.text = if (result4) "4. 맞았습니다" else "4. 틀렸습니다"

        val problem1 = findViewById<ImageView>(R.id.problem1)
        problem1.setImageResource(if (result1) R.drawable.correct else R.drawable.wrong)

        val problem2 = findViewById<ImageView>(R.id.problem2)
        problem2.setImageResource(if (result2) R.drawable.correct else R.drawable.wrong)

//        val problem3 = findViewById<ImageView>(R.id.problem3)
//        problem3.setImageResource(if (result3) R.drawable.correct else R.drawable.wrong)
//
//        val problem4 = findViewById<ImageView>(R.id.problem4)
//        problem4.setImageResource(if (result4) R.drawable.correct else R.drawable.wrong)
//
//        // 클릭 이벤트 처리 잠금화면
        problem1.setOnClickListener {
            startWebViewActivity("https://youtu.be/DSkR2nc3uUE", "잠금화면에서 홈화면으로 이동하세요.")
        }
        // 상단바
        problem2.setOnClickListener {
            startWebViewActivity("https://youtu.be/aRTg0jgNQvo", "상단바를 내리세요.")
        }
//
//        // 밝기 조절
//        problem3.setOnClickListener {
//            startWebViewActivity("https://youtu.be/rLWAD9LW4aY", "화면 밝기를 올리세요.")
//        }
//
//        // 앱 꺼내기
//        problem4.setOnClickListener {
//            startWebViewActivity("https://youtube.com/shorts/t5foNMcSMn0", "플레이 스토어를 홈 화면으로 이동하시오.")
//        }

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
            val intent = Intent(this, ExamCameraProblemActivity::class.java)
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


