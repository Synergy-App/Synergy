package com.sungkyul.synergy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityMainBinding

/** 메인화면: 로고+교육공간, 실습공간, 내 공간, 환경설정 버튼 */
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.eduMenuBtn.setOnClickListener{
            val intent = Intent(this, EduSpaceActivity::class.java)
            startActivity(intent)
        }
    }
}
