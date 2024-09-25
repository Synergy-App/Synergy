package com.sungkyul.synergy.training_space.default_app.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCamera3Binding
import com.sungkyul.synergy.learning_space.default_app.gallery.activity.BottomNavItem
import com.sungkyul.synergy.learning_space.default_app.gallery.fragment.DefaultGalleryAlbumFragment
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCamera3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCamera3Binding

    private lateinit var pictureFragment: Fragment
    private lateinit var albumFragment: Fragment
    private lateinit var bottomNavItems: List<BottomNavItem>

    private val enabledColor = Color.parseColor("#252525")
    private val disabledColor = Color.parseColor("#858585")

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCamera3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트들을 초기화한다.
        pictureFragment = DefaultGalleryPictureExamFragment()
        albumFragment = DefaultGalleryAlbumFragment(binding.eduScreen)

        // 하단 바의 버튼들을 리스트로 묶는다.
        bottomNavItems = listOf(
            BottomNavItem(pictureFragment, binding.pictureButtonText, binding.pictureButtonLine),
            BottomNavItem(albumFragment, binding.albumButtonText, binding.albumButtonLine),
        )

        // 각 버튼의 인터랙션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.groupingSimilarImagesButton)
        AnimUtils.initTouchButtonAnimation(binding.makingMovieButton)
        AnimUtils.initTouchButtonAnimation(binding.searchButton)
        AnimUtils.initTouchButtonAnimation(binding.moreButton)
        AnimUtils.initTouchButtonAnimation(binding.pictureButton)
        AnimUtils.initTouchButtonAnimation(binding.albumButton)
        AnimUtils.initTouchButtonAnimation(binding.storyButton)
        AnimUtils.initTouchButtonAnimation(binding.shareButton)

        // '비슷한 이미지 묶기' 버튼의 터치 리스너를 설정한다.
        binding.groupingSimilarImagesButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '영화 만들기' 버튼의 터치 리스너를 설정한다.
        binding.makingMovieButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '검색' 버튼의 터치 리스너를 설정한다.
        binding.searchButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '더보기' 버튼의 터치 리스너를 설정한다.
        binding.moreButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '사진' 버튼의 터치 리스너를 설정한다.
        binding.pictureButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomNavItem(pictureFragment)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(pictureFragment)
                    setButtonsVisibility(pictureFragment)
                }
            }
            true
        }

        // '앨범' 버튼의 터치 리스너를 설정한다.
        binding.albumButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomNavItem(albumFragment)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(albumFragment)
                    setButtonsVisibility(albumFragment)
                }
            }
            true
        }
//        // '스토리' 버튼의 터치 리스너를 설정한다.
//        binding.storyButton.setOnTouchListener { view, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    AnimUtils.startTouchDownButtonAnimation(this, view)
//
//                    selectBottomNavItem(storyFragment)
//                }
//                MotionEvent.ACTION_UP -> {
//                    AnimUtils.startTouchUpButtonAnimation(this, view)
//
//                    replaceFragment(storyFragment)
//                    setButtonsVisibility(storyFragment)
//                }
//            }
//            true
//        }
//
//        // '공유' 버튼의 터치 리스너를 설정한다.
//        binding.shareButton.setOnTouchListener { view, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    AnimUtils.startTouchDownButtonAnimation(this, view)
//
//                    selectBottomNavItem(shareFragment)
//                }
//                MotionEvent.ACTION_UP -> {
//                    AnimUtils.startTouchUpButtonAnimation(this, view)
//
//                    replaceFragment(shareFragment)
//                    setButtonsVisibility(shareFragment)
//                }
//            }
//            true
//        }

        selectBottomNavItem(pictureFragment)
        replaceFragment(pictureFragment)
        startTimer(remainingTimeInMillis)


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

    // 프래그먼트를 교체한다.
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    // 각 버튼의 보여짐 여부를 fragment에 따라 설정한다.
    private fun setButtonsVisibility(fragment: Fragment) {
        // 갤러리/사진 화면에 있을 때만 '비슷한 이미지 묶기', '영화 만들기' 버튼을 보여준다.
        if (fragment == pictureFragment) {
            binding.groupingSimilarImagesButton.visibility = ImageButton.VISIBLE
            binding.makingMovieButton.visibility = ImageButton.VISIBLE
        } else {
            binding.groupingSimilarImagesButton.visibility = ImageButton.INVISIBLE
            binding.makingMovieButton.visibility = ImageButton.INVISIBLE
        }
    }

    private fun selectBottomNavItem(fragment: Fragment) {
        // 각 아이템의 텍스트 색상과 밑줄 보여짐 여부를 변경한다.
        for (i in bottomNavItems) {
            if (i.fragment == fragment) {
                i.text.setTextColor(enabledColor)
                i.line.visibility = ImageView.VISIBLE
            } else {
                i.text.setTextColor(disabledColor)
                i.line.visibility = ImageView.INVISIBLE
            }
        }
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeCameraPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("apple_result", isSuccess)
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