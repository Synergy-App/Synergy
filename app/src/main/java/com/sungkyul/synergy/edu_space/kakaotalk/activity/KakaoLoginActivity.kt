package com.sungkyul.synergy.edu_space.kakaotalk.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityKakaoLoginBinding

/** 카카오톡 로그인 화면 */
private lateinit var binding: ActivityKakaoLoginBinding

class KakaoLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
