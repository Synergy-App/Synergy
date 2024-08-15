package com.sungkyul.synergy.training_space.setting.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.message.PracticeMessageListActivity
import com.sungkyul.synergy.training_space.setting.PraticeSettingActivity

class ExamSettingActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "글씨 크기를 최대로 높여보세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PraticeSettingActivity::class.java)
            startActivity(intent)
        }
    }
}