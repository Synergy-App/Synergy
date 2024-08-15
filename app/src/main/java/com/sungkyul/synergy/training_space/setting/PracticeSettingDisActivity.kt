package com.sungkyul.synergy.training_space.setting

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.settings.SettingsDisplayCourse
import com.sungkyul.synergy.databinding.ActivityPracticeSettingDisBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.settingedu.SettingFontActivity
import com.sungkyul.synergy.learning_space.settingedu.SettingMainActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PracticeSettingDisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeSettingDisBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeSettingDisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)

        startTimer()

        // setting2_display_font_btn 버튼 찾기
        val displayFontBtn = findViewById<Button>(R.id.setting2_display_font_btn)

        // displayFontBtn 클릭 이벤트 처리
        displayFontBtn.setOnClickListener {
            // Setting2FontActivity로 이동하는 Intent 생성
            val intent = Intent(this, PracticeSettingFontActivity::class.java)
            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis)
            startActivity(intent)
        }

        // 시크바 변경 이벤트 처리
        binding.sbBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 시스템 화면 밝기 설정하기 (progress 값으로 밝기 조절)
                val brightnessValue = progress / 100.0f // SeekBar의 max가 100이므로 비율로 환산
                val layoutParams = window.attributes
                layoutParams.screenBrightness = brightnessValue // 화면 밝기 설정
                window.attributes = layoutParams

                binding.eduScreen.onAction("change_light_bar", progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {

        }
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
    private fun startTimer() {
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                binding.timerTextView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                // 타이머가 끝나면 할 작업
            }
        }
        timer.start()
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel()
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer()
        }
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
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "글자 크기를 최대로 높여보세요."
        messageTextView.textSize = 20f

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
            // returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}
