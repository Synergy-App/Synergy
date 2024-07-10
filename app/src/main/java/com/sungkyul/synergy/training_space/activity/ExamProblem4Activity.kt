package com.sungkyul.synergy.training_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.screen.PracticeAppMoveActivity

class ExamProblem4Activity  : AppCompatActivity() {

    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "플레이 스토어를 홈 화면에 배치하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeAppMoveActivity::class.java)
            startActivity(intent)
        }
    }
}