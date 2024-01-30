package com.sungkyul.synergy.edu_space.defaultapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityPhoneBinding
import com.sungkyul.synergy.util.Animation

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Animation.setViewBgAlphaAnimationOnTouch(
            binding.magnifyingGlassButton,
            0,
            0,
            36,
            300
        )
        Animation.setViewBgAlphaAnimationOnTouch(
            binding.moreButton,
            0,
            0,
            36,
            300
        )
    }
}
