package com.sungkyul.synergy.training_space.install

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityAppinstallMainBinding
import com.sungkyul.synergy.databinding.ActivityPracticeInstallBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.appinstall.AppInstallSearchActivity
import com.sungkyul.synergy.training_space.install.problem.ExamInstallActivity
import com.sungkyul.synergy.training_space.install.result.ExamInstallResultActivity
import com.sungkyul.synergy.training_space.message.result.ExamMessageResultActivity

class PracticeInstallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeInstallBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeInstallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        val recommendTextView: TextView = findViewById(R.id.install_recommend_text)

        // "추천" 부분의 인덱스 찾기
        val text = recommendTextView.text.toString()
        val index = text.indexOf("추천")

        // "추천" 부분의 텍스트 크기 변경
        recommendTextView.textSize = 16f // 기본 크기로 설정
        val spannable = SpannableString(text)
        spannable.setSpan(
            AbsoluteSizeSpan(20, true),
            index,
            index + 2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        recommendTextView.text = spannable

        val appInstallEdit: EditText = findViewById(R.id.app_install_edit)
        // 문제보기 클릭 시 다이얼로그 띄우기

        startTimer(remainingTimeInMillis)
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true

        // EditText에 텍스트 변경 이벤트 리스너 추가
        appInstallEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().contains("카", ignoreCase = true)) {
                    appInstallEdit.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                            (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)
                        ) {
                            // 검색 버튼(엔터)을 눌렀을 때만 검색 화면으로 이동
                            val intent = Intent(
                                this@PracticeInstallActivity,
                                PracticeInstall2Activity::class.java
                            )
                            startActivity(intent)
                            return@setOnEditorActionListener true
                        }
                        return@setOnEditorActionListener false
                    }
                }
            }
        })


    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            pausedTimeInMillis = remainingTimeInMillis
            timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
            isTimerRunning = false
        }
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
        messageTextView.text = "'카카오톡'을 검색하고 '카카오톡 어플을 설치하시오."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            // 타이머를 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.setOnShowListener {
            timer.cancel() // 다이얼로그가 열릴 때 타이머 멈춤
            isTimerRunning = false
        }

        alertDialog.setOnDismissListener {
            // 다이얼로그가 닫힐 때 타이머 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.show()

        // 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeInstallPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("install_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamInstallResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)
    }
}

