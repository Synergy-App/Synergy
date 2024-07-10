
package com.sungkyul.synergy.training_space.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sungkyul.synergy.databinding.ActivityPracticeTopBarBinding
import com.sungkyul.synergy.training_space.activity.ExamProblem3Activity

class PracticeScreenTopScroll2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeTopBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeTopBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3초 후에 다이얼로그를 표시
        Handler(Looper.getMainLooper()).postDelayed({
            gotoProblem()
        }, 3000) // 3000 milliseconds = 3 seconds

    }

    private fun gotoProblem() {
        val intent = Intent(this, ExamProblem3Activity::class.java)
        startActivity(intent)
        finish()
    }
}
