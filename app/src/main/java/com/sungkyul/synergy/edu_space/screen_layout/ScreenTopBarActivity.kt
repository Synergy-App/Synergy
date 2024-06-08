package com.sungkyul.synergy.edu_space.screen_layout

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class ScreenTopBarActivity : AppCompatActivity() {

    private var startY = 0f
    private var endY = 0f
    private var wifiOn = false
    private var soundState = 0
    private var bluetoothOn = false
    private var screenLockOn = false
    private var flightOn = false
    private var powerSavingOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_topbar)

        // 하단바 숨기기 설정
        hideSystemUI()

        findViewById<View>(R.id.drag_area).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    endY = event.rawY
                    if (startY - endY > 100) { // 드래그 거리 설정 (100px 이상 위로 드래그 시)
                        showHomeScreen()
                        true
                    } else {
                        false
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 터치 이벤트가 끝날 때
                    startY = 0f
                    endY = 0f
                    false
                }
                else -> false
            }
        }

        val wifiImageView = findViewById<ImageView>(R.id.wifi_image)
        wifiImageView.setOnClickListener {
            wifiOn = !wifiOn
            wifiImageView.setImageResource(if (wifiOn) R.drawable.ic_wifi_on else R.drawable.ic_wifi_off)
        }

        val soundImageView = findViewById<ImageView>(R.id.sound_image)
        soundImageView.setOnClickListener {
            soundState = (soundState + 1) % 3
            val soundResId = when (soundState) {
                0 -> R.drawable.ic_sound_off
                1 -> R.drawable.ic_sound_on
                else -> R.drawable.ic_sound_on_va
            }
            soundImageView.setImageResource(soundResId)
        }

        val bluetoothImageView = findViewById<ImageView>(R.id.bluetooth_image)
        bluetoothImageView.setOnClickListener {
            bluetoothOn = !bluetoothOn
            bluetoothImageView.setImageResource(if (bluetoothOn) R.drawable.ic_bluetooth_on else R.drawable.ic_bluetooth_off)
        }

        val screenLockImageView = findViewById<ImageView>(R.id.screen_lock_image)
        screenLockImageView.setOnClickListener {
            screenLockOn = !screenLockOn
            screenLockImageView.setImageResource(if (screenLockOn) R.drawable.ic_lock_on else R.drawable.ic_lock_off)
        }

        val flightImageView = findViewById<ImageView>(R.id.flight_image)
        flightImageView.setOnClickListener {
            flightOn = !flightOn
            flightImageView.setImageResource(if (flightOn) R.drawable.ic_plane_on else R.drawable.ic_plane_off)
        }

        val powerSavingImageView = findViewById<ImageView>(R.id.power_saving_image)
        powerSavingImageView.setOnClickListener {
            powerSavingOn = !powerSavingOn
            powerSavingImageView.setImageResource(if (powerSavingOn) R.drawable.ic_flash_on else R.drawable.ic_flash_off)
        }
    }

    private fun showHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_up, R.anim.stay)
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
}
