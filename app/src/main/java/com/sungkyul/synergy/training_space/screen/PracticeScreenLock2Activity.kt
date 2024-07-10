package com.sungkyul.synergy.training_space.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.activity.ExamProblem2Activity

/** 잠금화면 풀어서 홈화면. (결과) */

class PracticeScreenLock2Activity : AppCompatActivity() {
    private var startY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_screen_lock2)


        // 3초 후에 다이얼로그를 표시
        Handler(Looper.getMainLooper()).postDelayed({
            gotoProblem()
        }, 3000) // 3000 milliseconds = 3 seconds

    }

    private fun gotoProblem() {
        val intent = Intent(this, ExamProblem2Activity::class.java)
        startActivity(intent)
        finish()
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

    }

//        // 전체 레이아웃 터치 이벤트 처리
//        findViewById<ConstraintLayout>(R.id.constraint_layout).setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    startY = event.y
//                    true
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//                    if (startY - event.y > 100) {
//                        showMenuScreen()
//                        true
//                    } else {
//                        false
//                    }
//                }
//
//                else -> false
//            }
//        }
//
//        // 툴바 터치 이벤트 처리
//        findViewById<View>(R.id.include_toolbar).setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    startY = event.y
//                    true
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//                    if (event.y - startY > 100) { // 아래로 드래그 거리 설정 (100px 이상 드래그 시)
//                        showTopbarScreen()
//                        true
//                    } else {
//                        false
//                    }
//                }
//
//                else -> false
//            }
//        }
//
//        // 투명한 뷰 터치 이벤트 처리
//        findViewById<View>(R.id.transparent_view_1).setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> true
//                MotionEvent.ACTION_UP -> {
//                    showRecentlyScreen()
//                    true
//                }
//
//                else -> false
//            }
//        }
//
//        findViewById<View>(R.id.transparent_view_2).setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> true
//                MotionEvent.ACTION_UP -> {
//                    returnToHomeScreen()
//                    true
//                }
//
//                else -> false
//            }
//        }
//
//        findViewById<View>(R.id.transparent_view_3).setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> true
//                MotionEvent.ACTION_UP -> {
//                    onBackPressed()
//                    true
//                }
//
//                else -> false
//            }
//        }
//    }
//
//    private fun showNextProblemDialog() {
//        val dialogBuilder = AlertDialog.Builder(this)
//
//        // 커스텀 레이아웃을 설정하기 위한 레이아웃 인플레이터
//        val inflater = this.layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
//
//        dialogBuilder.setView(dialogView)
//
//        val alertDialog = dialogBuilder.create()
//
//        // 다이얼로그 메시지 텍스트뷰 설정
//        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
//        numberTextView.text = "문제 3."
//
//        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
//        messageTextView.text = "상단바를 내려 화면 밝기를 조절하시오."
//        messageTextView.textSize = 20f
//
//        // 확인 버튼 설정
//        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
//        confirmButton.setOnClickListener {
//            alertDialog.dismiss() // 다이얼로그 닫기
//            // 다이얼로그가 닫힐 때 타이머 다시 시작
//        }
//        // 다이얼로그 표시
//        alertDialog.show()
//
//
//    }
//
//
//    private fun showMenuScreen() {
////        val intent = Intent(this, ScreenMenuActivity::class.java)
////        startActivity(intent)
//        overridePendingTransition(R.anim.slide_up, R.anim.stay)
//    }
//
//    private fun showTopbarScreen() {
//        val intent = Intent(this, PracticeTopBarActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_down, R.anim.stay)
//    }
//
//    private fun showRecentlyScreen() {
////        val intent = Intent(this, ScreenRecentlyActivity::class.java)
////        startActivity(intent)
//        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
//    }
//
//    private fun returnToHomeScreen() {
////        val intent = Intent(this, PracticeTopBarActivity::class.java)
////        startActivity(intent)
//        overridePendingTransition(R.anim.stay, R.anim.stay)
//    }


}

