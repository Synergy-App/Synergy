package com.sungkyul.synergy.learning_space

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityEduCompletionBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.accountedu.GoogleFirstActivity
import com.sungkyul.synergy.learning_space.appinstall.AppInstallFirstActivity
import com.sungkyul.synergy.learning_space.default_app.camera.activity.DefaultCameraFirstActivity
import com.sungkyul.synergy.learning_space.default_app.message.activity.DefaultMessageFirstActivity
import com.sungkyul.synergy.learning_space.default_app.phone.activity.DefaultPhoneFirstActivity
import com.sungkyul.synergy.learning_space.kakaotalk.activity.KakaoFirstActivity
import com.sungkyul.synergy.learning_space.naver.activity.NaverFirstActivity
import com.sungkyul.synergy.learning_space.screen_layout.ScreenFirstActivity
import com.sungkyul.synergy.learning_space.settingedu.SettingsFirstActivity

class EduCompletionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEduCompletionBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val homeIntent = Intent(this, MainActivity::class.java)
         var replayIntent = Intent(this, MainActivity::class.java)

        replayIntent = when(intent.getStringExtra("course")) {
            "accountedu" -> Intent(this, GoogleFirstActivity::class.java)
            "appinstall" -> Intent(this, AppInstallFirstActivity::class.java)
            "camera" -> Intent(this, DefaultCameraFirstActivity::class.java)
            "kakao" -> Intent(this, KakaoFirstActivity::class.java)
            "message" -> Intent(this, DefaultMessageFirstActivity::class.java)
            "naver" -> Intent(this, NaverFirstActivity::class.java)
            "phone" -> Intent(this, DefaultPhoneFirstActivity::class.java)
            "screen_layout" -> Intent(this, ScreenFirstActivity::class.java)
            "settings" -> Intent(this, SettingsFirstActivity::class.java)
            else -> Intent(this, MainActivity::class.java)
        }

        binding.goHomeButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //(view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    //(view as GalaxyButton).startTouchUpAnimation()

                    startActivity(homeIntent)
                }
            }
            true
        }
        binding.replayButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //(view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    //(view as GalaxyButton).startTouchUpAnimation()

                    startActivity(replayIntent)
                }
            }
            true
        }
    }
}