package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCall2Binding
import com.sungkyul.synergy.learning_space.default_app.CALL_ENDED_DELAY
import com.sungkyul.synergy.learning_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_SCALE
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_SCALE
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_SCALE
import com.sungkyul.synergy.learning_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.utils.AnimUtils


///여기 약간 오류 잇네..
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

        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    // saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
               //   returnToHomeScreen()
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        // 커스텀 레이아웃을 설정하기 위한 레이아웃 인플레이터
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)

        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 텍스트뷰 설정
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 2."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "통화 종료 후 최근기록에서 통화내역을 확인히세요."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
          //  returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, PracticeCall2ResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }


//    private fun saveResult(isSuccess: Boolean) {
//        val sharedPreferences =
//            getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putBoolean("move_app_success", isSuccess)
//        editor.apply()
//    }


    // 녹음 버튼의 터치 이벤트 리스너
    private val onTouchRecordingListener = View.OnTouchListener { view, event ->
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

    // 영상통화 버튼의 터치 이벤트 리스너
    private val onTouchVideoCallListener = View.OnTouchListener { view, event ->
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

    // 블루투스 버튼의 터치 이벤트 리스너
    private val onTouchBluetoothListener = View.OnTouchListener { view, event ->
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

    // 스피커 버튼의 터치 이벤트 리스너
    private val onTouchSpeakerListener = View.OnTouchListener { view, event ->
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

    // 내 소리 차단 버튼의 터치 이벤트 리스너
    private val onTouchMuteMyAudioListener = View.OnTouchListener { view, event ->
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

    // 키패드 버튼의 터치 이벤트 리스너
    private val onTouchKeypadListener = View.OnTouchListener { view, event ->
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

    // 끊기 버튼의 터치 이벤트 리스너 TODO ================
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

    // 연락처 보기 버튼의 터치 이벤트 리스너
    private val onTouchViewContactListener = View.OnTouchListener { view, event ->
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

    // 통화 버튼의 터치 이벤트 리스너
    private val onTouchCallListener = View.OnTouchListener { view, event ->
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

    // 메시지 버튼의 터치 이벤트 리스너
    private val onTouchMessageListener = View.OnTouchListener { view, event ->
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
            val intent = Intent(this, PracticeCall2ResultActivity::class.java)
            startActivity(intent)

        }, CALL_ENDED_DELAY)
    }
}
