package com.sungkyul.synergy.training_space.google.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.google.PracticeGoogleActivity

class ExamGoogleProblem  : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "구글 계정 생성을 완료하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeGoogleActivity::class.java)
            startActivity(intent)
        }
    }
}