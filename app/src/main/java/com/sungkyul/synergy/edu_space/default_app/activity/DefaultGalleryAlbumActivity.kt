package com.sungkyul.synergy.edu_space.default_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryBinding

class DefaultGalleryAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}