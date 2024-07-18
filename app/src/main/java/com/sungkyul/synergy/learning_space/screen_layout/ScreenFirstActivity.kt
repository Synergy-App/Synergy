package com.sungkyul.synergy.learning_space.screen_layout

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.courses.screen_layout.ScreenFirstCourse
import com.sungkyul.synergy.databinding.ActivityScreenFirstBinding
import com.sungkyul.synergy.home.activity.MainActivity

class ScreenFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = ScreenFirstCourse(binding.eduScreen)

            binding.sebookTitle.visibility = LinearLayout.INVISIBLE
            binding.sebookAll.visibility = ImageView.VISIBLE
            binding.eduScreen.setOnNextListener {num->
                if(num==1 || num==2) {
                    binding.sebookTitle.visibility = LinearLayout.VISIBLE
                    binding.sebookAll.visibility = ImageView.INVISIBLE
                } else {
                    binding.sebookTitle.visibility = LinearLayout.INVISIBLE
                    binding.sebookAll.visibility = ImageView.VISIBLE
                }
            }
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.
                val intent = Intent(binding.root.context, ScreenLockActivity::class.java)
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
