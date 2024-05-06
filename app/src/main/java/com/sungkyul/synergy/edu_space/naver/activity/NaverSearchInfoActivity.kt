package com.sungkyul.synergy.edu_space.naver.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityNaverSearchInfoBinding

class NaverSearchInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverSearchInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 여기에 추가적인 작업 수행
    }
}
