package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGooglePutCodeBinding
import com.sungkyul.synergy.edu_courses.accountedu.GooglePutCodeCourse

class GooglePutCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGooglePutCodeBinding

    private var countDownTimer: CountDownTimer? = null
    private var countdownTime: Long = 30 // 초기 카운트다운 시간 (초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGooglePutCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GooglePutCodeCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        startCountdown()

        binding.putCodeEdittext.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // 포커스가 있을 때는 G- 텍스트를 유지합니다.
                binding.putCodeEdittext.hint = "G-"
            } else {
                // 포커스가 없을 때도 G- 텍스트를 유지합니다.
                binding.putCodeEdittext.hint = "G-"
            }
        }

        binding.putNextButton.setOnClickListener {
            val nextIntent = Intent(this, GoogleMailAddActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)

            startActivity(nextIntent)
        }
        // editText의 텍스트 변경 감지
        binding.putCodeEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("code_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })
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
                binding.countdownText.setTextColor(Color.parseColor("#1B76EB"))
                binding.countdownText.text = "새 코드 받기"
                binding.countdownText.setOnClickListener {
                    finish() // 현재 액티비티를 종료하여 뒤로 가기 버튼을 눌렀을 때 이전 액티비티로 돌아가지 않도록 합니다.
                }
            }
        }.start()
    }

    private fun updateCountdownText() {
        binding.countdownText.text = "새 코드 받기(${countdownTime.toString()}초)"
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
