package com.sungkyul.synergy.training_space.default_app.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeResultGalleryBinding
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.training_space.default_app.camera.problem.ExamCameraProblem4Activity
import com.sungkyul.synergy.training_space.default_app.camera.result.ExamCameraResultActivity
import com.sungkyul.synergy.utils.AnimUtils

class PracticeResultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeResultGalleryBinding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeResultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dotButton.setOnTouchListener(onTouchDeleteListener)

        // RecyclerView 설정
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_photos)


        startTimer(remainingTimeInMillis)

        // 가로 방향으로 스크롤 설정
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // 사진 데이터 (예시로 몇 개의 drawable을 사용)
        val photoList = listOf(
            R.drawable.sample_screenshot1,
            R.drawable.sample_screenshot2,
            R.drawable.screenedu_background,
            R.drawable.defaultback1,
            R.drawable.ic_appinstall_synergylogo_storke,
            R.drawable.korean_flag,
            R.drawable.sample_screenshot2,
            R.drawable.screenedu_background,
            R.drawable.gallery_apple,

            R.drawable.gallery_apple
        )

        // 어댑터 설정
        recyclerView.adapter = GalleryPhotoAdapter(this, photoList)
    }

    private val onTouchDeleteListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_UP_ALPHA,
                    TOUCH_DOWN_ALPHA
                )
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_DOWN_ALPHA,
                    TOUCH_UP_ALPHA
                )

                showDialog()


                view.performClick()
            }
        }
        true
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setMessage("이 이미지를 휴지통으로 이동할까요?")

        builder.setPositiveButton("휴지통으로 이동") { dialog, _ ->
            // 확인 버튼 클릭 시 작업
            dialog.dismiss()

            saveResult(false) // 실패 결과 저장

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, ExamCameraResultActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }

        builder.setNegativeButton("취소") { dialog, _ ->
            // 취소 버튼 클릭 시 작업
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
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
            startTimer(pausedTimeInMillis)
        }
    }

    private fun showRecentlyScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, PracticeCamera2Activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeCameraPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("camera_apple_result", isSuccess)
        editor.apply()
    }


    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                showRecentlyScreen()
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
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
        numberTextView.text = "문제 2."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "갤러리에서 '사과'사진을 삭제하시오."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            saveResult(true) // 성공 결과 저장
            startTimer(remainingTimeInMillis) // 타이머 다시 시작
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }
}
