package com.sungkyul.synergy.training_space.naver.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.google.PracticeGoogleActivity
import com.sungkyul.synergy.training_space.naver.PracticeNaverActivity

class ExamNaverProblem  : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "된장찌개 만드는 방법을 검색창에 입력하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeNaverActivity::class.java)
            startActivity(intent)
        }
    }
}