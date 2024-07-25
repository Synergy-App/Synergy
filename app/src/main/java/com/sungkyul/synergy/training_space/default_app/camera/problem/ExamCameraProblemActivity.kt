package com.sungkyul.synergy.training_space.default_app.camera.problem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.learning_space.default_app.gallery.activity.DefaultGalleryActivity
import com.sungkyul.synergy.training_space.default_app.camera.PracticeCamera3Activity
import com.sungkyul.synergy.training_space.default_app.camera.PracticeCameraActivity
import com.sungkyul.synergy.training_space.screen.PracticeTopBarActivity

// 1. 후면카메라로 사진을 찍고 사진을 확인해보세요.
class ExamCameraProblemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "후면카메라로 사진을 찍고 사진을 확인해보세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCameraActivity::class.java)
            startActivity(intent)
        }
    }
}