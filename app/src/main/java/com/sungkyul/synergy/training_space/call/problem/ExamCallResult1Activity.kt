package com.sungkyul.synergy.training_space.call.problem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamCallResult1Binding
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.training_space.call.PracticeCallActivity

class ExamCallResult1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExamCallResult1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamCallResult1Binding.inflate(layoutInflater)
        setContentView(binding.root)

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


        // 3초 후에 성공 메시지 표시
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ExamCallProblem2Activity::class.java)
            startActivity(intent)
        }, 3000)
    }
}