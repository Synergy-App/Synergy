package com.sungkyul.synergy.edu_space.screen_layout

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.databinding.ActivityScreenLayoutBinding
import com.sungkyul.synergy.utils.EduCourses

class ScreenLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 customCourse를 지정한다.
            binding.eduScreen.course = EduCourses.screenLayoutCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }
}
