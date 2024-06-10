package com.sungkyul.synergy.edu_space.screen_layout

import android.app.ActivityOptions
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityScreenMenuBinding

class ScreenMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 하단바 숨기기 설정
        hideSystemUI()

        // 아이콘 레이아웃에 롱클릭 리스너 추가
        val playstoreIcon = findViewById<View>(R.id.playstore_icon)
        playstoreIcon.setOnLongClickListener { view ->
            val intent = Intent(this, ScreenMoveHomeActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this, playstoreIcon, "icon_transition")

            // 드래그 앤 드롭 관련 데이터 설정
            val clipData = ClipData.newPlainText("icon_tag", view.tag.toString())
            val shadowBuilder = DragShadowBuilder(view)
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
