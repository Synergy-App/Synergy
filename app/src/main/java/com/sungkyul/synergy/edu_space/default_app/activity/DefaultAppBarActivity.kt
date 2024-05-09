package com.sungkyul.synergy.edu_space.default_app.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.provider.Settings
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Button
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.sungkyul.synergy.R

class DefaultAppBarActivity : AppCompatActivity() {

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var imageView45: ImageView
    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var linearLayout2: LinearLayout
    private lateinit var overlayLayout: ViewGroup
    private lateinit var imageView52: ImageView
    private lateinit var rootView: View // 전체 화면을 기준으로 설정
    private var toolbar1Visible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_app_bar)

        imageView52 = findViewById(R.id.imageView52)
        appBarLayout = findViewById(R.id.appBarLayout)
        imageView45 = findViewById(R.id.imageView45)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton2 = findViewById(R.id.imageButton2)
        imageButton1 = findViewById(R.id.imageButton1)
        linearLayout2 = findViewById(R.id.linearLayout2)
        overlayLayout = findViewById(R.id.overlayLayout)
        rootView = findViewById(android.R.id.content) // 전체 화면을 가져옴


        val brightnessSeekBar = findViewById<SeekBar>(R.id.brightnessSeekBar)

        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // 사용자가 조절한 밝기 값으로 시스템 설정 변경
                    val brightnessValue = progress.toFloat() / 255
                    if (!Settings.System.canWrite(applicationContext)) {
                        // 권한이 없을 경우 설정으로 이동
                        Toast.makeText(applicationContext, "밝기 조절 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                        intent.data = Uri.parse("package:$packageName")
                        startActivity(intent)
                    } else {
                        // 설정 변경
                        val params = Settings.System.getFloat(contentResolver, Settings.System.SCREEN_BRIGHTNESS)
                        Settings.System.putFloat(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightnessValue)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        imageButton1.setOnClickListener {
            appBarLayout.setExpanded(false)
            linearLayout2.bringToFront()
            appBarLayout.requestLayout()

            imageView45.setImageResource(R.drawable.taskmgr)
            imageView45.bringToFront()

            linearLayout2.bringToFront()
        }

        imageButton2.setOnClickListener {
            val intent = Intent(applicationContext, DefaultAppBarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        imageButton3.setOnClickListener {
            val intent = Intent(applicationContext, DefaultAppBarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        // rootView에 터치 이벤트 리스너 설정
        rootView.setOnTouchListener(object : View.OnTouchListener {
            private var downY: Float = 0.0f
            private var upY: Float = 0.0f

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downY = event.y
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        upY = event.y
                        if (upY - downY > 20) {
                            // 아래로 슬라이드할 때 수행할 동작 추가
                            // 예를 들어 overlayLayout의 visibility 변경 등
                            slideOverlayLayout()
                            return true
                        }
                    }
                }
                return false
            }
        })

        findViewById<Button>(R.id.button44).setOnClickListener {
            // Toggle between toolbar1 and toolbar2
            if (toolbar1Visible) {
                imageView52.setImageResource(R.drawable.toolbar2)
            } else {
                imageView52.setImageResource(R.drawable.toolbar1)
            }
            toolbar1Visible = !toolbar1Visible
        }
    }

    private fun slideOverlayLayout() {
        val animation = TranslateAnimation(0f, 0f, -overlayLayout.height.toFloat(), 0f)
        animation.duration = 500
        overlayLayout.startAnimation(animation)
        overlayLayout.visibility = View.VISIBLE
    }
}