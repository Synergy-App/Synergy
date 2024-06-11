package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.learning_space.screen.PracticeTopBarActivity

class ExamProblem3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "화면 밝기를 조절하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeTopBarActivity::class.java)
            startActivity(intent)
        }
    }
}