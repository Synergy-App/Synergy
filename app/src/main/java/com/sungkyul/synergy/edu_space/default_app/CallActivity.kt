package com.sungkyul.synergy.edu_space.default_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultCallBinding
import com.sungkyul.synergy.databinding.ActivityDefaultCallPhoneBinding
import com.sungkyul.synergy.util.AnimationUtil

class CallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        binding.phoneNumText.text = "휴대전화 "+intent.getStringExtra("phone_num")

        // 배경 알파 값 초기화
        binding.whiteBackground.drawable.alpha = 0
        binding.recordingButton.background.alpha = TOUCH_UP_ALPHA
        binding.videoCallButton.background.alpha = TOUCH_UP_ALPHA
        binding.bluetoothButton.background.alpha = TOUCH_UP_ALPHA
        binding.speakerButton.background.alpha = TOUCH_UP_ALPHA
        binding.muteMyAudioButton.background.alpha = TOUCH_UP_ALPHA
        binding.keypadButton.background.alpha = TOUCH_UP_ALPHA
        binding.hangUpButton.background.alpha = TOUCH_UP_ALPHA
        binding.viewContactButton.background.alpha = TOUCH_UP_ALPHA
        binding.callButton.background.alpha = TOUCH_UP_ALPHA
        binding.messageButton.background.alpha = TOUCH_UP_ALPHA
        binding.videoCallButton2.background.alpha = TOUCH_UP_ALPHA

        // 버튼의 이벤트 리스너 연결
        binding.recordingButton.setOnTouchListener(onTouchRecordingListener)
        binding.videoCallButton.setOnTouchListener(onTouchVideoCallListener)
        binding.bluetoothButton.setOnTouchListener(onTouchBluetoothListener)
        binding.speakerButton.setOnTouchListener(onTouchSpeakerListener)
        binding.muteMyAudioButton.setOnTouchListener(onTouchMuteMyAudioListener)
        binding.keypadButton.setOnTouchListener(onTouchKeypadListener)
        binding.hangUpButton.setOnTouchListener(onTouchHangUpListener)
        binding.viewContactButton.setOnTouchListener(onTouchViewContactListener)
        binding.callButton.setOnTouchListener(onTouchCallListener)
        binding.messageButton.setOnTouchListener(onTouchMessageListener)
        binding.videoCallButton2.setOnTouchListener(onTouchVideoCallListener)
    }

    // 녹음 버튼의 터치 이벤트 리스너
    private val onTouchRecordingListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 영상통화 버튼의 터치 이벤트 리스너
    private val onTouchVideoCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 블루투스 버튼의 터치 이벤트 리스너
    private val onTouchBluetoothListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 스피커 버튼의 터치 이벤트 리스너
    private val onTouchSpeakerListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 내 소리 차단 버튼의 터치 이벤트 리스너
    private val onTouchMuteMyAudioListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 키패드 버튼의 터치 이벤트 리스너
    private val onTouchKeypadListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 끊기 버튼의 터치 이벤트 리스너
    private val onTouchHangUpListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                hangOut()

                view.performClick()
            }
        }
        true
    }

    // 연락처 보기 버튼의 터치 이벤트 리스너
    private val onTouchViewContactListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 통화 버튼의 터치 이벤트 리스너
    private val onTouchCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 메시지 버튼의 터치 이벤트 리스너
    private val onTouchMessageListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimationUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 전화를 끊었을 때, 통화 종료에 맞게 화면을 꾸미는 함수
    private fun hangOut() {
        // 배경 화면을 하얀색으로 변경한다.
        AnimationUtil.startAlphaAnimation(binding.whiteBackground.drawable, 200L, 0, 255)

        // 통화 종료를 알려준다.
        binding.callStatusText.text = "통화 종료"
        binding.callStatusText.setTextColor(ContextCompat.getColor(this, R.color.phoneRed))

        // 이름을 검은색으로 변경한다.
        binding.nameText.setTextColor(ContextCompat.getColor(this, R.color.black))

        // 필요 없는 뷰를 숨긴다.
        binding.phoneNumText.visibility = View.INVISIBLE
        binding.hangUpButton.visibility = View.INVISIBLE
        binding.recordingButton.visibility = View.GONE
        binding.videoCallButton.visibility = View.GONE
        binding.bluetoothButton.visibility = View.GONE
        binding.speakerButton.visibility = View.GONE
        binding.muteMyAudioButton.visibility = View.GONE
        binding.keypadButton.visibility = View.GONE

        // 필요한 뷰를 보여준다.
        binding.viewContactButton.visibility = View.VISIBLE
        binding.callButton.visibility = View.VISIBLE
        binding.messageButton.visibility = View.VISIBLE
        binding.videoCallButton2.visibility = View.VISIBLE

        // 몇 초 후에 화면을 종료한다.
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, CALL_ENDED_DELAY)
    }
}
