package com.sungkyul.synergy.training_space.install.problem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.install.PracticeInstallActivity
import com.sungkyul.synergy.training_space.message.PracticeMessageListActivity

class ExamInstallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "플레이스토어에서 '카카오톡' 어플을 설치하세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeInstallActivity::class.java)
            startActivity(intent)
        }
    }
}