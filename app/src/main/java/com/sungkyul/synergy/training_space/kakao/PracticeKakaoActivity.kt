package com.sungkyul.synergy.training_space.kakao

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoMainBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.training_space.kakao.fragment.Friends2Fragment

// Fragment 태그 상수 정의
private const val TAG_FRIENDS = "friends_fragment"
private const val TAG_CHAT = "chat_fragment"
private const val TAG_OPENCHAT = "openChat_fragment"
private const val TAG_SHOPPING = "shopping_fragment"
private const val TAG_MORE = "more_fragment"

class PracticeKakaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMainBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = remainingTimeInMillis // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        startTimer() // 타이머 시작

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }


        // 초기 Fragment 설정
        setFragment(TAG_FRIENDS, Friends2Fragment())

        // 버튼 클릭 리스너 설정
        binding.friendButton.setOnClickListener {
            setFragment(TAG_FRIENDS, Friends2Fragment())
        }

        binding.chattingButton.setOnClickListener {
            if (binding.eduScreen.onAction("click_chatting_button")) {
                //  setFragment(TAG_CHAT, ChatFragment())
            }
        }

        binding.openchattingButton.setOnClickListener {
            // setFragment(TAG_OPENCHAT, OpenChatFragment())
        }

        binding.shoppingButton.setOnClickListener {
            // setFragment(TAG_SHOPPING, ShoppingFragment())
        }

        binding.moreButton.setOnClickListener {
            // setFragment(TAG_MORE, MoreFragment())
        }

        // 뒤로 가기 키 이벤트 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
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
                // 타이머가 끝났을 때의 동작 추가
            }
        }
        timer.start() // 타이머 시작
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
        messageTextView.text = "'임영웅'님께 카카오톡 메세지를 보내세요."
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
        val intent = Intent(this, PracticeKakao2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

    // Fragment 설정 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        // 해당 태그의 Fragment가 없는 경우 추가
        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.kakaoMainFrameLayout, fragment, tag)
        }

        // 이미 추가된 Fragment의 상태에 따라 보여주기/숨기기
        val friends = manager.findFragmentByTag(TAG_FRIENDS)
        val chat = manager.findFragmentByTag(TAG_CHAT)

        friends?.let {
            if (tag == TAG_FRIENDS) {
                fragTransaction.show(it)
            } else {
                fragTransaction.hide(it)
            }
        }

        chat?.let {
            if (tag == TAG_CHAT) {
                fragTransaction.show(it)
            } else {
                fragTransaction.hide(it)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}
