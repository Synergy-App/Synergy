package com.sungkyul.synergy.edu_space.default_app.phone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneCallBinding
import com.sungkyul.synergy.edu_space.default_app.CALL_ENDED_DELAY
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_SCALE
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_SCALE
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_SCALE
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.edu.EduCourses

class DefaultPhoneCallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultPhoneCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultPhoneCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 defaultPhoneCallCourse를 지정한다.
            binding.eduScreen.course = EduCourses.defaultPhoneCallCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

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
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 영상통화 버튼의 터치 이벤트 리스너
    private val onTouchVideoCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 블루투스 버튼의 터치 이벤트 리스너
    private val onTouchBluetoothListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 스피커 버튼의 터치 이벤트 리스너
    private val onTouchSpeakerListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 내 소리 차단 버튼의 터치 이벤트 리스너
    private val onTouchMuteMyAudioListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 키패드 버튼의 터치 이벤트 리스너
    private val onTouchKeypadListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 끊기 버튼의 터치 이벤트 리스너
    private val onTouchHangUpListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                if(binding.eduScreen.onAction("click_hang_up_button")) {
                    hangOut()
                }

                view.performClick()
            }
        }
        true
    }

    // 연락처 보기 버튼의 터치 이벤트 리스너
    private val onTouchViewContactListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 통화 버튼의 터치 이벤트 리스너
    private val onTouchCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 메시지 버튼의 터치 이벤트 리스너
    private val onTouchMessageListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtils.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 전화를 끊었을 때, 통화 종료에 맞게 화면을 꾸미는 함수
    private fun hangOut() {
        // 배경 화면을 하얀색으로 변경한다.
        AnimUtils.startAlphaAnimation(binding.whiteBackground.drawable, 200L, 0, 255)

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
            val intent = Intent(this, DefaultPhoneActivity::class.java)
            intent.putExtra("from", "call")
            startActivity(intent)
        }, CALL_ENDED_DELAY)
    }
}
