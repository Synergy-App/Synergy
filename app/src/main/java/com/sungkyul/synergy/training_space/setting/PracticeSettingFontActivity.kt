package com.sungkyul.synergy.training_space.setting

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeSettingFontBinding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PracticeSettingFontActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeSettingFontBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeSettingFontBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 남은 시간 가져오기
        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)

        startTimer() // 타이머 시작


        // 뒤로 가기 버튼 클릭 시 이벤트 처리
        binding.fontBackButton.setOnClickListener {
            startActivity(Intent(this, PracticeSettingDisActivity::class.java))
        }

        // 시크바를 초기화한다.
        binding.textsizeSeekbar.progress = 0
        binding.mainText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.main_text_size_0)
        )
        binding.textsizeSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val textSize = when (progress) {
                    0 -> resources.getDimension(R.dimen.main_text_size_0)
                    1 -> resources.getDimension(R.dimen.main_text_size_1)
                    2 -> resources.getDimension(R.dimen.main_text_size_2)
                    3 -> resources.getDimension(R.dimen.main_text_size_3)
                    4 -> resources.getDimension(R.dimen.main_text_size_4)
                    5 -> resources.getDimension(R.dimen.main_text_size_5)
                    6 -> resources.getDimension(R.dimen.main_text_size_6)
                    7 -> resources.getDimension(R.dimen.main_text_size_7)
                    else -> resources.getDimension(R.dimen.main_text_size_default)
                }

                binding.mainText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

                // 글자 크기가 최대로 높아졌을 때
                if (progress == 7 && fromUser) {
                    Toast.makeText(
                        this@PracticeSettingFontActivity,
                        "글자 크기가 최대로 설정되었습니다!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // 타이머가 실행 중일 때 멈추기
                    if (isTimerRunning) {
                        timer.cancel()
                        isTimerRunning = false
                        binding.timerTextView.text = "성공" // 타이머 중지 상태를 표시
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun startTimer() {
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (isTimerRunning) {
                    remainingTimeInMillis = millisUntilFinished
                    binding.timerTextView.text = (millisUntilFinished / 1000).toString()
                } else {
                    cancel() // 타이머가 이미 멈춘 경우 중지
                }
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
