package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.naver.PracticeNaverActivity

/// 문제 1 : 네이버 된장찌개 검색 문제 화면
class ExamNaverProblemActivity: AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "네이버에 '된장찌개 만드는 방법'를 검색하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeNaverActivity::class.java)
            startActivity(intent)
        }
    }
}