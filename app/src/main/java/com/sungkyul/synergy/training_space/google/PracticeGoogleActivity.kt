package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogleBinding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.setting.PracticeSettingFontActivity
import com.sungkyul.synergy.training_space.setting.result.ExamSettingResultActivity

class PracticeGoogleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeGoogleBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = remainingTimeInMillis // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = findViewById<TextView>(R.id.guest_code_text)
        val textData = textView.text.toString()

        val grayColor = Color.parseColor("#808080") // 회색
        val blueColor = Color.parseColor("#1B76EB") // 파란색

        // SpannableStringBuilder를 사용하여 텍스트의 일부분의 색상을 변경합니다.
        val builder = SpannableStringBuilder(textData)

        // "게스트 코드 사용 방법 자세히 알아보기" 부분을 파란색으로 변경
        val startIndex = textData.indexOf("게스트 코드 사용 방법 자세히 알아보기")
        val endIndex = startIndex + "게스트 코드 사용 방법 자세히 알아보기".length
        builder.setSpan(
            ForegroundColorSpan(blueColor),
            startIndex,
            endIndex,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 나머지 텍스트를 회색으로 변경
        builder.setSpan(
            ForegroundColorSpan(grayColor),
            0,
            startIndex,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 변경된 Spannable 문자열을 TextView에 설정합니다.
        textView.text = builder

        // "계정 만들기" 버튼을 찾습니다.
        val createAccountButton = findViewById<Button>(R.id.create_account_button)

        // "계정 만들기" 버튼에 클릭 리스너를 추가합니다.
        createAccountButton.setOnClickListener {
            // 다이얼로그를 표시합니다.
            showAccountTypeDialog()
        }

        startTimer() // 타이머 시작

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
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
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                saveResult(false) // 실패 결과 저장
                isTimerRunning = false
                showHomeScreen()
            }
        }.start()
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
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "구글 계정 생성을 완료하시오."
        messageTextView.textSize = 20f

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            success = true // 문제 풀이 성공으로 표시
            timer.cancel() // 다이얼로그 닫기와 동시에 타이머 멈춤
            isTimerRunning = false

            // 타이머를 재시작
            startTimer(remainingTimeInMillis) // 남은 시
            // returnToHomeScreen() // 홈 화면으로 이동 (필요한 경우 사용)
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

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeGooglePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("google_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamSettingResultActivity::class.java)
        startActivity(intent)
    }


    // 다이얼로그 표시 함수
    private fun showAccountTypeDialog() {
        // 다이얼로그를 생성하고 다이얼로그의 레이아웃을 설정합니다.
        val dialogView = layoutInflater.inflate(R.layout.activity_google_dialog, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // "개인용" 버튼을 찾습니다.
        val personalButton = dialogView.findViewById<Button>(R.id.btn_personal)

        // "개인용" 버튼에 클릭 리스너를 추가합니다.
        personalButton.setOnClickListener {
            // 화면을 이동시킵니다. (이동할 화면의 액티비티를 여기에 지정합니다.)
            val intent = Intent(this, PracticeGoogle2Activity::class.java)
            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
            startActivity(intent)

            // 다이얼로그를 닫습니다.
            dialog.dismiss()
        }


        // 다이얼로그를 화면에 표시합니다.
        dialog.show()

        // 다이얼로그의 위치를 조정합니다.
        val layoutParams = dialog.window?.attributes
        layoutParams?.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM // 중앙 아래로 정렬
        layoutParams?.y = 340 // 아래로부터의 거리를 조절합니다. 적절한 값을 설정해주세요.
        dialog.window?.attributes = layoutParams
    }
}