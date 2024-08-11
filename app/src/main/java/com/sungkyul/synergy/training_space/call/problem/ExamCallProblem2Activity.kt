package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.call.PracticeCall2Activity
import com.sungkyul.synergy.training_space.call.PracticeCallActivity
import com.sungkyul.synergy.training_space.default_app.camera.PracticeCamera3Activity

/// 문제 2 : 최근기록
class ExamCallProblem2Activity: AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "통화를 종료 후 최근 기록에서 통화내역을 확인하세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCall2Activity::class.java)
            startActivity(intent)
        }
    }
}