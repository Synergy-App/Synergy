package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.view.ViewCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeAppMoveBinding
import com.sungkyul.synergy.databinding.ActivityPracticeScreenLockPracticeBinding
import com.sungkyul.synergy.edu_space.screen_layout.ScreenHomeActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenMoveHomeActivity

class PracticeAppMoveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeAppMoveBinding

    @SuppressLint( "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeAppMoveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이콘 레이아웃에 롱클릭 리스너 추가
        binding.playstoreIcon.setOnLongClickListener { view ->
            val intent = Intent(this, PracticeAppMove2Activity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this, binding.playstoreIcon, "icon_transition")

            // 드래그 앤 드롭 관련 데이터 설정
            val clipData = ClipData.newPlainText("icon_tag", view.tag.toString())
            val shadowBuilder = View.DragShadowBuilder(view)
            ViewCompat.startDragAndDrop(view, clipData, shadowBuilder, null, View.DRAG_FLAG_GLOBAL)

            // startActivity를 호출하여 ScreenMoveHomeActivity로 전환
            startActivity(intent, options.toBundle())
            true
        }

        // 투명한 뷰 터치 이벤트 처리
        findViewById<View>(R.id.transparent_view_2).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    returnToHomeScreen()
                    true
                }

                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_3).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    onBackPressed()
                    true
                }

                else -> false
            }
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}