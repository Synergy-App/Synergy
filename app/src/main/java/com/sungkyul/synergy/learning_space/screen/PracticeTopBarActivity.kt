package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeTopBarBinding
import com.sungkyul.synergy.learning_space.activity.ExamProblem4Activity

class PracticeTopBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeTopBarBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 15000 // 초기 카운트 다운 시간 (15초)
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeTopBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시크바 설정
        val brightnessSeekBar = binding.brightnessSeekBar
        brightnessSeekBar.progress = getScreenBrightness()
        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (Settings.System.canWrite(this@PracticeTopBarActivity)) {
                    setScreenBrightness(progress)
                } else {
                    requestWriteSettingsPermission()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 타이머 초기화 및 시작
        startTimer(remainingTimeInMillis)

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    private fun showHomeScreen() {
        val intent = Intent(this, PracticeScreenLock2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)
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

        // 타이머 초기화 및 시작
        startTimer(remainingTimeInMillis)
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
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                    saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                showNextProblemDialog()
            }
        }

        timer.start()
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
        numberTextView.text = "문제 3."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "밝기를 조절하시오."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            startTimer(remainingTimeInMillis) // 타이머 다시 시작
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun getScreenBrightness(): Int {
        return try {
            Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            128 // 기본값으로 반환 (중간 밝기)
        }
    }

    private var isDialogShown = false

    private fun setScreenBrightness(brightness: Int) {
        val layoutParams = window.attributes
        layoutParams.screenBrightness = brightness / 255f
        window.attributes = layoutParams

        // System settings update
        if (Settings.System.canWrite(this)) {
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)

            success = true // 성공 여부를 true로 설정

            if (!isDialogShown) {
                isDialogShown = true // Set the flag to true to prevent multiple dialogs
                Handler().postDelayed({
                    showNextProblemDialog()
                }, 1000)
            }
        } else {
            requestWriteSettingsPermission()
        }
    }

    private fun requestWriteSettingsPermission() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivityForResult(intent, REQUEST_WRITE_SETTINGS)
    }

    private fun showNextProblemDialog() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamProblem4Activity::class.java)
        startActivity(intent)
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("brightness_adjust_success", isSuccess)
        editor.apply()
    }

    companion object {
        private const val REQUEST_WRITE_SETTINGS = 200
    }
}
