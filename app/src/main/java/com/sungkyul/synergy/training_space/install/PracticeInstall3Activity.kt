package com.sungkyul.synergy.training_space.install

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.DrawableCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeInstall3Binding
import com.sungkyul.synergy.databinding.ActivityPracticeInstallBinding
import com.sungkyul.synergy.training_space.install.result.ExamInstallResultActivity
import com.sungkyul.synergy.training_space.message.result.ExamMessageResultActivity

class PracticeInstall3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeInstall3Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeInstall3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        startTimer(remainingTimeInMillis)
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        // 카카오 로고 이미지 서서히 줄어드는 애니메이션
        val kakaoAppImage = findViewById<ImageView>(R.id.kakao_app_image)
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.kakao_logo_scale_down)
        kakaoAppImage.startAnimation(scaleDownAnimation)

        // 스피너 추가
        val spinner = findViewById<ProgressBar>(R.id.image_loading_spinner)
        spinner.visibility = View.VISIBLE
        // 스피너 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            spinner.indeterminateDrawable.setColorFilter(
                Color.parseColor("#0B57CF"), PorterDuff.Mode.SRC_IN
            )
        } else {
            val wrapDrawable = DrawableCompat.wrap(spinner.indeterminateDrawable)
            DrawableCompat.setTint(wrapDrawable, Color.parseColor("#0B57CF"))
            spinner.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
        }

        // 2.5초 후에 스피너와 after_kakao_app_image를 지우는 작업 예약
        Handler().postDelayed({
            spinner.visibility = View.GONE
            findViewById<ImageView>(R.id.after_kakao_app_image).visibility = View.VISIBLE
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 4%"
        }, 2500)


        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line2)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 17%"
        }, 3500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line3)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 30%"
        }, 4500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line4)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 49%"
        }, 5500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line5)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 77%"
        }, 6500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line6)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 100%"
        }, 7500)

        // 스피너를 보이게 하는 시점에만 색상 변경
        // 8.5초 후에 after_kakao_app_image를 안보이게 하고 스피너를 보이게 함
        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).visibility = View.GONE
            findViewById<TextView>(R.id.kakao_info_text).text = "설치 중..."
            spinner.visibility = View.VISIBLE
            // 스피너 색상 변경
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                spinner.indeterminateDrawable.setColorFilter(
                    Color.parseColor("#C6C6C6"), PorterDuff.Mode.SRC_IN
                )
            } else {
                val wrapDrawable = DrawableCompat.wrap(spinner.indeterminateDrawable)
                DrawableCompat.setTint(wrapDrawable, Color.parseColor("#C6C6C6"))
                spinner.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
            }
        }, 8500)

        // 11초 후에 after_kakao_app_image의 크기를 원본 사이즈로 확대하는 애니메이션
        Handler().postDelayed({
            binding.eduScreen.onAction("complete_loading")

            val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.kakao_logo_scale_up)
            findViewById<ImageView>(R.id.kakao_app_image).startAnimation(scaleUpAnimation)
            findViewById<TextView>(R.id.kakao_info_text).text = "설치됨"
            spinner.visibility = View.GONE
            findViewById<Button>(R.id.kakao_cancel_btn).visibility = View.GONE
            findViewById<Button>(R.id.after_kakao_install_btn).visibility = View.VISIBLE
        }, 11000)

        saveResult(true)
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