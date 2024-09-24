package com.sungkyul.synergy.training_space.message

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeMessageBinding
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageAdapter
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageData
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MyMessageData
import com.sungkyul.synergy.training_space.call.PracticeCall2ResultActivity
import com.sungkyul.synergy.training_space.message.result.ExamMessageResultActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DateTimeUtils
import java.time.LocalDateTime

class PracticeMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeMessageBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val now = LocalDateTime.now()
        val messageArray = ArrayList<MessageData>()

        val messages = binding.messages
        messages.layoutManager = LinearLayoutManager(binding.root.context)
        messages.adapter = MessageAdapter(messageArray)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)
        startTimer()

        // 버튼의 터치 애니메이션을 초기화한다.
        initTouchAnimations()

        binding.messageEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.eduScreen.onAction("click_message_edit_text")
            }
        }

        binding.sendButton.setOnTouchListener { view, event ->
            handleSendButtonTouch(view, event, now, messageArray)
            true
        }

        binding.goToTopMenuButton.setOnTouchListener(onTouchGoToTopMenuButtonListener)

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
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
        messageTextView.text = "010-1234-5678님께 문자를 보내세요."
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


    private fun initTouchAnimations() {
        // 버튼의 터치 애니메이션을 초기화한다.
        val buttons = listOf(
            binding.goToTopMenuButton,
            binding.callButton,
            binding.searchButton,
            binding.conversationSettingsButton,
            binding.imageButton,
            binding.cameraButton,
            binding.plusButton,
            binding.expandButton,
            binding.emojiButton,
            binding.recordButton,
            binding.sendButton
        )
        for (button in buttons) {
            AnimUtils.initTouchButtonAnimation(button)
            button.setOnTouchListener(onTouchButtonListener)
        }
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeMessagePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("message_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamMessageResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)
    }


    private val onTouchButtonListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startTouchDownButtonAnimation(this, view)
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startTouchUpButtonAnimation(this, view)
                view.performClick()
            }
        }
        true
    }

    private fun handleSendButtonTouch(
        view: View,
        event: MotionEvent,
        now: LocalDateTime,
        messageArray: ArrayList<MessageData>
    ) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startTouchDownButtonAnimation(this, view)
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startTouchUpButtonAnimation(this, view)

                // Check if the send button was clicked successfully
                if (binding.eduScreen.onAction("click_send_button")) {
                    val adapter = binding.messages.adapter as MessageAdapter

                    if (binding.messageEditText.text.toString().isNotEmpty()) {
                        // Create and add new message data
                        messageArray.add(
                            MyMessageData(
                                binding.messageEditText.text.toString(),
                                "${now.format(DateTimeUtils.dateFormatter)} ${
                                    DateTimeUtils.getKoreanDayOfWeek(now)
                                }",
                                "${DateTimeUtils.getKoreanPeriod(now)} ${
                                    now.format(DateTimeUtils.timeFormatter)
                                }"
                            )
                        )

                        // Notify adapter of the new item
                        adapter.notifyItemInserted(messageArray.size - 1)

                        // Clear the input field
                        binding.messageEditText.text.clear()
                        saveResult(true) // 실패 결과 저장
                        // Hide the keyboard
                        val inputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(
                            binding.messageEditText.windowToken,
                            0
                        )

                        // Set success to true immediately when the message is sent
                        success = true
                        Handler(Looper.getMainLooper()).postDelayed({
                            // 화면을 전환할 Activity로 Intent 생성
                            val intent = Intent(this,ExamMessageResultActivity::class.java)
                            startActivity(intent)
                            // 현재 Activity 종료 (선택사항)
                            finish()
                        }, 3000) // 3000ms = 3초
                    }
                    view.performClick()
                }
            }
        }
    }

    private val onTouchGoToTopMenuButtonListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startTouchDownButtonAnimation(this, view)
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startTouchUpButtonAnimation(this, view)
                view.performClick()
            }
        }
        true
    }
}
