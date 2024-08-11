package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.call.PracticeCallActivity
import com.sungkyul.synergy.training_space.default_app.camera.PracticeCamera3Activity

/// 문제 1 : 전화 걸기
class ExamCallProblemActivity: AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "010-2345-6789로 전화를 걸어보세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCallActivity::class.java)
            startActivity(intent)
        }
    }
}