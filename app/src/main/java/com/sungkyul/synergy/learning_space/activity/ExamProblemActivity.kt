package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.learning_space.screen.PracticeScreenLockActivity

class ExamProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "주어진 시간 내에 홈화면으로 이동하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeScreenLockActivity::class.java)
            startActivity(intent)
        }
    }
}