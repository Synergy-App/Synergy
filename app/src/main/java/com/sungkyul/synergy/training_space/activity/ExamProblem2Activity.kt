package com.sungkyul.synergy.training_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.screen.PracticeScreenTopScrollActivity

class ExamProblem2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "상단바를 내리세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeScreenTopScrollActivity::class.java)
            startActivity(intent)
        }
    }
}