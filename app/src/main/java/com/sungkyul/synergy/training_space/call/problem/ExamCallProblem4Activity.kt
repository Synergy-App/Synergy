package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.call.PracticeCall4Activity

/// 문제 4 : ㅅㅣ븍이 검색하는 실습문제
class ExamCallProblem4Activity: AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "연락처로 '시북이'를 검색하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCall4Activity::class.java)
            startActivity(intent)
        }
    }
}