package com.sungkyul.synergy.learning_space.default_app.message.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.courses.default_app.message.DefaultMessageFirstCourse
import com.sungkyul.synergy.databinding.ActivityDefaultMessageFirstBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.default_app.phone.activity.DefaultPhoneActivity

class DefaultMessageFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultMessageFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMessageFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = DefaultMessageFirstCourse(binding.eduScreen)

            binding.imageTitle.visibility = LinearLayout.INVISIBLE
            binding.sebookAll.visibility = ImageView.VISIBLE
            binding.eduScreen.setOnNextListener {num->
                if(num==1) {
                    binding.imageTitle.visibility = LinearLayout.VISIBLE
                    binding.sebookAll.visibility = ImageView.INVISIBLE
                }
            }
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.
                val intent = Intent(binding.root.context, DefaultMessageChattingActivity::class.java)
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
