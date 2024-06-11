package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
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
    private var startY = 0f
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 15000 // 초기 카운트 다운 시간 (15초)
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간

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

    private fun startTimer(startTimeInMillis: Long) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                // 타이머 종료 후 수행할 작업 추가
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 타이머 시작
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
        // 예: 새로운 문제를 보여주는 다이얼로그 또는 새로운 액티비티로 이동하는 코드
//        val dialogBuilder = AlertDialog.Builder(this)
//        val inflater = this.layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialoglayout, null) // 새로운 문제 레이아웃
//
//        dialogBuilder.setView(dialogView)
//        val alertDialog = dialogBuilder.create()
//
//        // 다이얼로그 메시지 텍스트뷰 설정
//        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
//        numberTextView.text = "문제 4."
//
//        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
//        messageTextView.text = "'플레이스토어' 앱을 실행한 후 홈 화면으로 이동하세요."
//        messageTextView.textSize = 20f
//
//        // 확인 버튼 설정
//        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
//        confirmButton.setOnClickListener {
//            alertDialog.dismiss() // 다이얼로그 닫기

//            val homeIntent = Intent(Intent.ACTION_MAIN)
//            homeIntent.addCategory(Intent.CATEGORY_HOME)
//            homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(homeIntent)

            val intent = Intent(this, ExamProblem4Activity::class.java)
            startActivity(intent)
        }

     //   alertDialog.show()

    companion object {
        private const val REQUEST_WRITE_SETTINGS = 200
    }
}
