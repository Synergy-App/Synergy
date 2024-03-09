package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class GooglePutCodeActivity : AppCompatActivity() {

    private lateinit var countdownText: TextView
    private lateinit var editText: EditText // 에딧텍스트 추가

    private var countDownTimer: CountDownTimer? = null
    private var countdownTime: Long = 30 // 초기 카운트다운 시간 (초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_put_code)

        countdownText = findViewById(R.id.countdown_text)
        editText = findViewById(R.id.put_code_edittext) // 에딧텍스트 초기화

        startCountdown()

        editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // 포커스가 있을 때는 G- 텍스트를 유지합니다.
                editText.hint = "G-"
            } else {
                // 포커스가 없을 때도 G- 텍스트를 유지합니다.
                editText.hint = "G-"
            }
        }

        val nextButton: Button = findViewById(R.id.put_next_button)
        nextButton.setOnClickListener {
            val nextIntent = Intent(this, GoogleMailAddActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)

            startActivity(nextIntent)
        }
    }

    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(countdownTime * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownTime--
                updateCountdownText()
            }

            override fun onFinish() {
                countdownTime = 0
                updateCountdownText()
                countdownText.setTextColor(Color.parseColor("#1B76EB"))
                countdownText.text = "새 코드 받기"
                countdownText.setOnClickListener {
                    finish() // 현재 액티비티를 종료하여 뒤로 가기 버튼을 눌렀을 때 이전 액티비티로 돌아가지 않도록 합니다.
                }
            }
        }.start()
    }

    private fun updateCountdownText() {
        countdownText.text = "새 코드 받기(${countdownTime.toString()}초)"
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
