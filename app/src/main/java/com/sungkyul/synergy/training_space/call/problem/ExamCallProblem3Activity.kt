package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.call.PracticeCall2Activity
import com.sungkyul.synergy.training_space.call.PracticeCall3Activity

// 전번 저장 실습 3번.
class ExamCallProblem3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "연락처에 다음과 같이 저장하세요\n이름: 시너지, \n전화번호:010-1111-1111"
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCall3Activity::class.java)
            startActivity(intent)
        }
    }
}