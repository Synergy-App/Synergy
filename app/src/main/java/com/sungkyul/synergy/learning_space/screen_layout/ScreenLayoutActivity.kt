package com.sungkyul.synergy.learning_space.screen_layout

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.databinding.ActivityScreenLayoutBinding
import com.sungkyul.synergy.courses.screen_layout.ScreenLayoutCourse

class ScreenLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // API 30 이상에서 사용 가능
        //window.insetsController?.hide(WindowInsets.Type.statusBars())
        //window.insetsController?.hide(WindowInsets.Type.systemBars())

        // Hide Status Bars
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Hide System Bars
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // 시스템 바의 높이를 구한다.
        val decorView = window.decorView
        var navigationBarHeight = 0
        decorView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            decorView.getWindowVisibleDisplayFrame(rect)
            val statusBarHeight = rect.top
            navigationBarHeight = decorView.height - rect.bottom
        }

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = ScreenLayoutCourse(binding.eduScreen)

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
