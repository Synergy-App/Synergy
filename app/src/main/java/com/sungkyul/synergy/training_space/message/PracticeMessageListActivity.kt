package com.sungkyul.synergy.training_space.message

import android.annotation.SuppressLint
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
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DateTimeUtils
import com.sungkyul.synergy.utils.GalaxyButton
import java.time.LocalDate

class PracticeMessageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeMessageListBinding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    private var scrolledUpward = true


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer()

        val date1 = LocalDate.of(2024, 12, 1)
        val date2 = LocalDate.of(2024, 1, 1)
        val now = LocalDate.now()

        val messageChattingArray = ArrayList<MessageChattingData>()
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "시너지",
                date1.format(DateTimeUtils.dateFormatter),
                "스위트허니너겟님"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "031-235-1046",
                date2.format(DateTimeUtils.dateFormatter),
                "[Web발신]\n[성결대학생상담센터친구상담사모집..."
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "02-1827-2938",
                date2.format(DateTimeUtils.dateFormatter),
                "ICT멘토링 사무국"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "114",
                date2.format(DateTimeUtils.dateFormatter),
                "[(주)시너지모바일] 1월 명세서\n이미지"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "031-273-1827",
                date2.format(DateTimeUtils.dateFormatter),
                "[Web발신] 인력 근무 인건비가 지급되었습니다."
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "010-2837-5826",
                date2.format(DateTimeUtils.dateFormatter),
                "[제목없음]"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "대장님",
                date2.format(DateTimeUtils.dateFormatter),
                "지금 뭐하나 자네"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "팀장님",
                date2.format(DateTimeUtils.dateFormatter),
                "어디 있는가 자네"
            )
        )
        messageChattingArray.add(
            MessageChattingData(
                R.drawable.ic_call_profile_24,
                "1898-7000",
                date2.format(DateTimeUtils.dateFormatter),
                "[시너지모바일] 개인정보 이용 내역 안내"
            )
        )
        for (i in 0..10) {
            messageChattingArray.add(
                MessageChattingData(
                    R.drawable.ic_call_profile_24,
                    "010-1234-9876",
                    date2.format(DateTimeUtils.dateFormatter),
                    "[제목없음]"
                )
            )
        }

        val messageChattingList = binding.messageChattingList
        messageChattingList.layoutManager = LinearLayoutManager(binding.root.context)
        messageChattingList.adapter =
            MessageChattingAdapter(this, messageChattingArray, binding.eduScreen.course)
        binding.eduScreen.post {
//            if (intent.getStringExtra("from") == "menu_button") {
//                messageChattingList.adapter =
//                    MessageChattingAdapter(this, messageChattingArray, binding.eduScreen.course)
//            }
        }

        // 메시지 채팅 목록의 스크롤 리스너를 추가한다.
        messageChattingList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 위로 스크롤하는 경우
                if (!scrolledUpward && dy < 0) {
                    AnimUtils.startAppearingButtonAnimation(binding.messageButton)
                    scrolledUpward = true
                }
                // 아래로 스크롤하는 경우
                if (scrolledUpward && dy > 0) {
                    AnimUtils.startDisappearingButtonAnimation(binding.messageButton)
                    scrolledUpward = false
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // 스크롤을 시작한 경우
                // 모든 버튼의 터치 애니메이션을 원래대로 되돌린다.
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    for (i in 0..<messageChattingArray.size) {
                        val galaxyButton =
                            messageChattingList.layoutManager?.findViewByPosition(i)
                                ?.findViewById<GalaxyButton>(R.id.galaxy_button)
                        if (galaxyButton != null && galaxyButton.getToggle()) {
                            galaxyButton.startTouchUpAnimation()
                        }
                    }
                }
            }
        })


        // 버튼의 터치 애니메이션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.magnifyingGlassButton)
        AnimUtils.initTouchButtonAnimation(binding.moreButton)


        // 버튼의 터치 리스너를 설정한다.
        binding.magnifyingGlassButton.setOnTouchListener { view, event ->
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
        // hiddenLayout 초기화
        binding.hiddenLayout.visibility = View.GONE
        // hiddenLayout이 숨겨져 있는지 여부를 나타내는 변수
        var isHidden = true

        binding.messageButton.setOnClickListener {
            if (isHidden) {
                // hiddenLayout을 보이도록 설정합니다
                binding.hiddenLayout.visibility = View.VISIBLE
                isHidden = false
                binding.eduScreen.onAction("message_button")
            } else {
                // hiddenLayout을 숨기도록 설정합니다
                binding.hiddenLayout.visibility = View.GONE
                isHidden = true
            }
        }

        //1:1 채팅 눌렀을 때
        binding.oneMessageButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    val intent = Intent(this, PracticeMessageActivity::class.java)
                    intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
                    startActivity(intent)                }
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
                //  returnToHomeScreen()
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
        messageTextView.text = "글자 크기를 최대로 높여보세요."
        messageTextView.textSize = 20f

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
            // returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}