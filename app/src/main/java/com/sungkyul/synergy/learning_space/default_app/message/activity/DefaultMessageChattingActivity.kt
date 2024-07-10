package com.sungkyul.synergy.learning_space.default_app.message.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageChattingBinding
import com.sungkyul.synergy.courses.default_app.message.DefaultMessageChattingCourse
import com.sungkyul.synergy.courses.default_app.message.DefaultMessageCourse3
import com.sungkyul.synergy.learning_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageChattingAdapter
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageChattingData
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DateTimeUtils
import com.sungkyul.synergy.utils.GalaxyButton
import java.time.LocalDate


class DefaultMessageChattingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultMessageChattingBinding

    private var scrolledUpward = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMessageChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            if (intent.getStringExtra("from") == "menu_button") {
                binding.eduScreen.course = DefaultMessageCourse3(binding.eduScreen)
            } else {
                binding.eduScreen.course = DefaultMessageChattingCourse(binding.eduScreen)
            }
            /*
            if(intent.getStringExtra("from") == null) {
                binding.eduScreen.course = DefaultMessageChattingCourse(binding.eduScreen)
            }

           // binding.eduScreen.course = DefaultMessageChattingCourse(binding.eduScreen)

            if(intent.getStringExtra("from") == "menu_button") {
                binding.eduScreen.course = DefaultMessageCourse3(binding.eduScreen)
            }*/

            binding.eduScreen.start(this)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

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
        for(i in 0..10) {
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
        messageChattingList.adapter = MessageChattingAdapter(this, messageChattingArray)

        // 메시지 채팅 목록의 스크롤 리스너를 추가한다.
        messageChattingList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 위로 스크롤하는 경우
                if(!scrolledUpward && dy < 0) {
                    AnimUtils.startAppearingButtonAnimation(binding.messageButton)
                    scrolledUpward = true
                }
                // 아래로 스크롤하는 경우
                if(scrolledUpward && dy > 0) {
                    AnimUtils.startDisappearingButtonAnimation(binding.messageButton)
                    scrolledUpward = false
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // 스크롤을 시작한 경우
                // 모든 버튼의 터치 애니메이션을 원래대로 되돌린다.
                if(newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    for(i in 0..<messageChattingArray.size) {
                        val galaxyButton = messageChattingList.layoutManager?.findViewByPosition(i)?.findViewById<GalaxyButton>(R.id.galaxy_button)
                        if(galaxyButton != null && galaxyButton.getToggle()) {
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
                    // DefaultMessageSelectActivity로 이동
                    val intent = Intent(this@DefaultMessageChattingActivity, DefaultMessageSelectActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }



}
}
