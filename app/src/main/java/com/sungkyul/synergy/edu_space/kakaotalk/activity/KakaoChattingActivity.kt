package com.sungkyul.synergy.edu_space.kakaotalk.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoChattingBinding
import com.sungkyul.synergy.utils.EduCourses

/** 카카오톡 채팅 내부 화면 */
class KakaoChattingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoChattingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eduScreen.post {
            binding.eduScreen.course = EduCourses.kakaoChatCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.start(this)
        }
    }
}
