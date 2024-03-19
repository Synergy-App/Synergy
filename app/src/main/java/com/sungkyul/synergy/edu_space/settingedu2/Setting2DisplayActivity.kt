package com.sungkyul.synergy.edu_space.settingedu2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivitySetting2DisplayBinding
import com.sungkyul.synergy.utils.edu.EduCourses

class Setting2DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetting2DisplayBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetting2DisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 settingsDisplayCourse를 지정한다.
            binding.eduScreen.course = EduCourses.settingsDisplayCourse(
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

        // setting_display_back_button 버튼 찾기
        val settingDisplayBackButton = findViewById<ImageButton>(R.id.setting_display_back_button)

        // settingDisplayBackButton 클릭 이벤트 처리
        settingDisplayBackButton.setOnClickListener {
            // Setting2MainActivity로 이동하는 Intent 생성
            val intent = Intent(this, Setting2MainActivity::class.java)
            startActivity(intent)
        }

        // setting2_display_font_btn 버튼 찾기
        val displayFontBtn = findViewById<Button>(R.id.setting2_display_font_btn)

        // displayFontBtn 클릭 이벤트 처리
        displayFontBtn.setOnClickListener {
            // Setting2FontActivity로 이동하는 Intent 생성
            val intent = Intent(this, Setting2FontActivity::class.java)
            startActivity(intent)
        }

        // 시크바 변경 이벤트 처리
        binding.sbBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 시스템 화면 밝기 설정하기 (progress 값으로 밝기 조절)
                val brightnessValue = progress / 100.0f // SeekBar의 max가 100이므로 비율로 환산
                val layoutParams = window.attributes
                layoutParams.screenBrightness = brightnessValue // 화면 밝기 설정
                window.attributes = layoutParams

                binding.eduScreen.onAction("change_light_bar", progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            if(binding.scrollView.getChildAt(0).bottom <= binding.scrollView.height+binding.scrollView.scrollY) {
                // 맨 아래에 도달한 경우
                binding.eduScreen.onAction("scroll_to_bottom")
                binding.scrollView.setOnTouchListener { _, _ -> true } // 스크롤 막기
            }
        }
    }
}
