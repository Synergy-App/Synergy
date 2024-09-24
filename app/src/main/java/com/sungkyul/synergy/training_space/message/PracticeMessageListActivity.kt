package com.sungkyul.synergy.training_space.message

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeMessageListBinding
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageChattingAdapter
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageChattingData
import com.sungkyul.synergy.training_space.call.PracticeCall2ResultActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.message.result.ExamMessageResultActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DateTimeUtils
import com.sungkyul.synergy.utils.GalaxyButton
import java.time.LocalDate

class PracticeMessageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeMessageListBinding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0
    private var scrolledUpward = true
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer()

        val messageChattingArray = ArrayList<MessageChattingData>().apply {
            add(MessageChattingData(R.drawable.ic_call_profile_24, "시너지", "2024-12-01", "스위트허니너겟님"))
            add(MessageChattingData(R.drawable.ic_call_profile_24, "031-235-1046", "2024-01-01", "[Web발신]\n[성결대학생상담센터친구상담사모집..."))
            // 추가 메시지 데이터
        }

        val messageChattingList = binding.messageChattingList
        messageChattingList.layoutManager = LinearLayoutManager(binding.root.context)
        messageChattingList.adapter = MessageChattingAdapter(this, messageChattingArray, binding.eduScreen.course)

        messageChattingList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!scrolledUpward && dy < 0) {
                    AnimUtils.startAppearingButtonAnimation(binding.messageButton)
                    scrolledUpward = true
                }
                if (scrolledUpward && dy > 0) {
                    AnimUtils.startDisappearingButtonAnimation(binding.messageButton)
                    scrolledUpward = false
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    for (i in 0 until messageChattingArray.size) {
                        val galaxyButton = messageChattingList.layoutManager?.findViewByPosition(i)?.findViewById<GalaxyButton>(R.id.galaxy_button)
                        galaxyButton?.takeIf { it.getToggle() }?.startTouchUpAnimation()
                    }
                }
            }
        })

        AnimUtils.initTouchButtonAnimation(binding.magnifyingGlassButton)
        AnimUtils.initTouchButtonAnimation(binding.moreButton)

        binding.magnifyingGlassButton.setOnTouchListener { view, event ->
            handleButtonTouch(event, view)
            true
        }
        binding.moreButton.setOnTouchListener { view, event ->
            handleButtonTouch(event, view)
            true
        }

        binding.hiddenLayout.visibility = View.GONE
        var isHidden = true

        binding.messageButton.setOnClickListener {
            if (isHidden) {
                binding.hiddenLayout.visibility = View.VISIBLE
                isHidden = false
                binding.eduScreen.onAction("message_button")
            } else {
                binding.hiddenLayout.visibility = View.GONE
                isHidden = true
            }
        }

        binding.oneMessageButton.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val intent = Intent(this, PracticeMessageActivity::class.java)
                intent.putExtra("remainingTimeInMillis", remainingTimeInMillis)
                startActivity(intent)
            }
            true
        }

        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        if (isTimerRunning) {
            timer.cancel()
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
                binding.timerTextView.text = (millisUntilFinished / 1000).toString()
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


    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "010-1234-1234님께 문자를 보내세요."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setOnShowListener {
            if (isTimerRunning) {
                timer.cancel() // 타이머 취소
                isTimerRunning = false
            }
        }

        alertDialog.setOnDismissListener {
            // 다이얼로그가 닫힐 때 타이머 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.show()
    }

    private fun handleButtonTouch(event: MotionEvent, view: View) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startTouchDownButtonAnimation(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startTouchUpButtonAnimation(this, view)
            }
        }
    }
}
