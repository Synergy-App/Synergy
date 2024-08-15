package com.sungkyul.synergy.training_space.message

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeMessageBinding
import com.sungkyul.synergy.learning_space.default_app.message.activity.DefaultMessageChattingActivity
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageAdapter
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageData
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MyMessageData
import com.sungkyul.synergy.training_space.call.PracticeCall2ResultActivity
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

        val dateTime1 = LocalDateTime.of(2023, 12, 1, 0, 0)
        val dateTime2 = LocalDateTime.of(2023, 12, 2, 7, 42)
        val dateTime3 = LocalDateTime.of(2023, 12, 3, 15, 36)
        val now = LocalDateTime.now()

        val messageArray = ArrayList<MessageData>()


        val messages = binding.messages
        messages.layoutManager = LinearLayoutManager(binding.root.context)
        messages.adapter = MessageAdapter(messageArray)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)
        startTimer()

        // 버튼의 터치 애니메이션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.goToTopMenuButton)
        AnimUtils.initTouchButtonAnimation(binding.callButton)
        AnimUtils.initTouchButtonAnimation(binding.searchButton)
        AnimUtils.initTouchButtonAnimation(binding.conversationSettingsButton)
        AnimUtils.initTouchButtonAnimation(binding.imageButton)
        AnimUtils.initTouchButtonAnimation(binding.cameraButton)
        AnimUtils.initTouchButtonAnimation(binding.plusButton)
        AnimUtils.initTouchButtonAnimation(binding.expandButton)
        AnimUtils.initTouchButtonAnimation(binding.emojiButton)
        AnimUtils.initTouchButtonAnimation(binding.recordButton)
        AnimUtils.initTouchButtonAnimation(binding.sendButton)

        // 버튼의 터치 리스너를 설정한다.
        binding.goToTopMenuButton.setOnTouchListener(onTouchGoToTopMenuButtonListener)
        binding.callButton.setOnTouchListener(onTouchCallButtonListener)
        binding.searchButton.setOnTouchListener(onTouchSearchButtonListener)
        binding.conversationSettingsButton.setOnTouchListener(
            onTouchConversationSettingsButtonListener
        )
        binding.imageButton.setOnTouchListener(onTouchImageButtonListener)
        binding.cameraButton.setOnTouchListener(onTouchCameraButtonListener)
        binding.plusButton.setOnTouchListener(onTouchPlusButtonListener)
        binding.expandButton.setOnTouchListener(onTouchExpandButtonListener)
        binding.emojiButton.setOnTouchListener(onTouchEmojiButtonListener)
        binding.recordButton.setOnTouchListener(onTouchRecordButtonListener)
        binding.sendButton.setOnTouchListener(onTouchSendButtonListener)

        binding.messageEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.eduScreen.onAction("click_message_edit_text")
            }
        }
        binding.sendButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    if (binding.eduScreen.onAction("click_send_button")) {
                        val adapter = messages.adapter as MessageAdapter

                        if (binding.messageEditText.text.toString().isNotEmpty()) {
                            messageArray.add(
                                MyMessageData(
                                    binding.messageEditText.text.toString(),
                                    "${now.format(DateTimeUtils.dateFormatter)} ${
                                        DateTimeUtils.getKoreanDayOfWeek(
                                            now
                                        )
                                    }",
                                    "${DateTimeUtils.getKoreanPeriod(now)} ${
                                        now.format(
                                            DateTimeUtils.timeFormatter
                                        )
                                    }"
                                )
                            )

                            // 아이템을 추가한다.
                            adapter.notifyItemRangeInserted(
                                messageArray.size - 1,
                                messageArray.size
                            )

                            binding.messageEditText.text.clear()
                        }

                        // 키보드를 숨긴다.
                        val inputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(
                            binding.messageEditText.windowToken,
                            0
                        )
                    }
                    returnToHomeScreen()
                    view.performClick()
                }
            }
            true
        }

        //메뉴 돌아가는 버튼
        binding.goToTopMenuButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    if (binding.eduScreen.onAction("menu_button")) {
//                            val intent = Intent(this, DefaultMessageChattingActivity::class.java)
//                            intent.putExtra("from", "menu_button")
//                            startActivity(intent)
                    }
                }
            }
            true
        }

        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
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
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    // saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                //   returnToHomeScreen()
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
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "010-1234-5678님께 문자를 보내세요."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
            //  returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
//        val intent = Intent(this, ExamMessageActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.stay, R.anim.stay)
        //TODO 메세지 전송 성공 시 어디로 보내야할까용?
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


    private val onTouchCallButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchSearchButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchConversationSettingsButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchImageButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchCameraButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchPlusButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchExpandButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchEmojiButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchRecordButtonListener = View.OnTouchListener { view, event ->
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

    private val onTouchSendButtonListener = View.OnTouchListener { view, event ->
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