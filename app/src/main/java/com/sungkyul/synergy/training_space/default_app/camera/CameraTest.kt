package com.sungkyul.synergy.training_space.default_app.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sungkyul.synergy.databinding.ActivityDefaultCameraBinding

/**카메라 실습 테스트입니다.* */
class CameraTest : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shootingButton.setOnClickListener {
            Toast.makeText(this, "잘 했습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.topMenuLayout.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.galleryButton.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.settingsButton.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.filterButton.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.timerButton.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }
        binding.bottomMenuLayout.setOnClickListener {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }

    }
}