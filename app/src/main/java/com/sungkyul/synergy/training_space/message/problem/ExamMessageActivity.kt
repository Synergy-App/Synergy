package com.sungkyul.synergy.training_space.message.problem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.message.PracticeMessageListActivity

class ExamMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "010-1234-1234님께 문자를 보내세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeMessageListActivity::class.java)
            startActivity(intent)
        }
    }
}