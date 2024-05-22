package com.sungkyul.synergy.edu_space.settingedu2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.databinding.ActivitySetting2MainBinding
import com.sungkyul.synergy.edu_courses.settings.SettingsCourse

class Setting2MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetting2MainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetting2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = SettingsCourse(binding.eduScreen)

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

        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            if(binding.scrollView.getChildAt(0).bottom <= binding.scrollView.height+binding.scrollView.scrollY) {
                // 맨 아래에 도달한 경우
                binding.eduScreen.onAction("scroll_to_bottom")
                binding.scrollView.setOnTouchListener { _, _ -> true } // 스크롤 막기
            }
        }

        // setting_display_btn 클릭 이벤트 처리
        binding.settingDisplayBtn.setOnClickListener {
            if(binding.eduScreen.onAction("tap_display_item")) {
                // Setting2DisplayActivity로 이동하는 Intent 생성
                val intent = Intent(this, Setting2DisplayActivity::class.java)
                startActivity(intent)
            }


        }
    }
}
