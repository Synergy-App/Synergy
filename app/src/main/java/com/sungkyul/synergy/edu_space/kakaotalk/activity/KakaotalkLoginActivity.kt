package com.sungkyul.synergy.edu_space.kakaotalk.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityKakaotalkLoginBinding

/** 카카오톡 로그인 화면 */
private lateinit var binding: ActivityKakaotalkLoginBinding

class KakaotalkLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaotalkLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
