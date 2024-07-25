package com.sungkyul.synergy.training_space.default_app.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.default_app.camera.problem.ExamCameraProblem2Activity
import com.sungkyul.synergy.training_space.default_app.camera.problem.ExamCameraProblemActivity

class PracticeCamera2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_camera2)

        // 3초 후에 다이얼로그를 표시
        Handler(Looper.getMainLooper()).postDelayed({
            gotoProblem()
        }, 3000) // 3000 milliseconds = 3 seconds

    }

    private fun gotoProblem() {
        val intent = Intent(this, ExamCameraProblem2Activity::class.java)
        startActivity(intent)
        finish()
    }
}