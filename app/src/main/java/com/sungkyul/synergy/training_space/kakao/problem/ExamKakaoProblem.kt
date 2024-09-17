package com.sungkyul.synergy.training_space.kakao.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.kakao.PracticeKakaoActivity
import com.sungkyul.synergy.training_space.naver.PracticeNaverActivity

class ExamKakaoProblem  : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "'임영웅'님께 카카오톡 메세지를 보내세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeKakaoActivity::class.java)
            startActivity(intent)
        }
    }
}