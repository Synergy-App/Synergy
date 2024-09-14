package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCall2Binding
import com.sungkyul.synergy.learning_space.default_app.CALL_ENDED_DELAY
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_SCALE
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_SCALE
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_SCALE
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCall2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCall2Binding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCall2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer()

        // 배경 알파 값 초기화
        binding.whiteBackground.drawable.alpha = 0
        setButtonAlpha(TOUCH_UP_ALPHA)

        // 버튼의 이벤트 리스너 연결
        setTouchListeners()

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            timer.cancel() // 타이머 취소
            isTimerRunning = false // 상태 업데이트
            pausedTimeInMillis = remainingTimeInMillis // 남은 시간을 저장
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis) // 남은 시간으로 타이머 시작
        }
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                if (!success) {
                    // saveResult(false) // 실패 결과 저장
                }
            }
        }

        timer.start() // 타이머 시작
        isTimerRunning = true // 타이머가 실행 중임을 표시
    }

    private fun showProblemDialog() {
        // 다이얼로그가 나타나면 타이머 멈춤
        if (isTimerRunning) {
            timer.cancel() // 타이머 취소
            isTimerRunning = false // 상태 업데이트
        }

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 2."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "통화 종료 후 최근기록에서 통화내역을 확인히세요."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss()
            success = true
           // saveResult(true)  // 성공 결과 저장
          //  returnToHomeScreen()
        }

        alertDialog.setOnDismissListener {
            // 다이얼로그가 닫힐 때 타이머 다시 시작
            if (!isTimerRunning) {
                startTimer(remainingTimeInMillis) // 남은 시간으로 타이머 시작
            }
        }

        alertDialog.show()
    }

    private fun setButtonAlpha(alphaValue: Int) {
        binding.recordingButton.background.alpha = alphaValue
        binding.videoCallButton.background.alpha = alphaValue
        binding.bluetoothButton.background.alpha = alphaValue
        binding.speakerButton.background.alpha = alphaValue
        binding.muteMyAudioButton.background.alpha = alphaValue
        binding.keypadButton.background.alpha = alphaValue
        binding.hangUpButton.background.alpha = alphaValue
        binding.viewContactButton.background.alpha = alphaValue
        binding.callButton.background.alpha = alphaValue
        binding.messageButton.background.alpha = alphaValue
        binding.videoCallButton2.background.alpha = alphaValue
    }

    private fun setTouchListeners() {
        binding.recordingButton.setOnTouchListener(onTouchListener)
        binding.videoCallButton.setOnTouchListener(onTouchListener)
        binding.bluetoothButton.setOnTouchListener(onTouchListener)
        binding.speakerButton.setOnTouchListener(onTouchListener)
        binding.muteMyAudioButton.setOnTouchListener(onTouchListener)
        binding.keypadButton.setOnTouchListener(onTouchListener)
        binding.hangUpButton.setOnTouchListener(onTouchHangUpListener)
        binding.viewContactButton.setOnTouchListener(onTouchListener)
        binding.callButton.setOnTouchListener(onTouchListener)
        binding.messageButton.setOnTouchListener(onTouchListener)
        binding.videoCallButton2.setOnTouchListener(onTouchListener)
    }

    private val onTouchListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_UP_ALPHA,
                    TOUCH_DOWN_ALPHA
                )
                AnimUtils.startScaleAnimation(
                    view,
                    TOUCH_DURATION_SCALE,
                    TOUCH_UP_SCALE,
                    TOUCH_DOWN_SCALE
                )
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_DOWN_ALPHA,
                    TOUCH_UP_ALPHA
                )
                view.performClick()
            }
        }
        true
    }

    // 끊기 버튼의 터치 이벤트 리스너
    private val onTouchHangUpListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_UP_ALPHA,
                    TOUCH_DOWN_ALPHA
                )
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_DOWN_ALPHA,
                    TOUCH_UP_ALPHA
                )

                if (binding.eduScreen.onAction("click_hang_up_button")) {
                    hangOut()
                }

                view.performClick()
            }
        }
        true
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeRecentlyDefaultPrefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("move_app_success", isSuccess)
            commit()  // 동기식으로 저장
        }
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, PracticeCall2ResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

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
        binding.viewContactButton.visibility = View.GONE
        binding.callButton.visibility = View.GONE
        binding.messageButton.visibility = View.GONE
        binding.videoCallButton2.visibility = View.GONE
        binding.timerTextView.visibility = View.INVISIBLE

        // 통화 종료 후 지연시간 설정
        Handler(Looper.getMainLooper()).postDelayed({
            returnToHomeScreen()
        }, CALL_ENDED_DELAY) // 지정된 지연 후 화면 전환
    }
}
