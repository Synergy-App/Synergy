package com.sungkyul.synergy.edu_space.default_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryBinding

class DefaultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
