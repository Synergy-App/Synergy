package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageBinding
import com.sungkyul.synergy.databinding.ActivityDefaultMessageChattingBinding
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageChattingAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageChattingData
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageData
import com.sungkyul.synergy.edu_space.default_app.adapter.MyMessageData
import com.sungkyul.synergy.edu_space.default_app.adapter.YourMessageData
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DateTimeUtils
import com.sungkyul.synergy.utils.DynamicButton
import java.time.LocalDate
import java.time.LocalDateTime

class DefaultMessageChattingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultMessageChattingBinding

    private var scrolledUpward = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMessageChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date1 = LocalDate.of(2024, 12, 1)
        val date2 = LocalDate.of(2024, 1, 1)
        val now = LocalDate.now()

        val messageChattingArray = ArrayList<MessageChattingData>()
        messageChattingArray.add(MessageChattingData(
            R.drawable.ic_call_profile_24,
            "A",
            date1.format(DateTimeUtils.dateFormatter),
            "a"
        ))
        messageChattingArray.add(MessageChattingData(
            R.drawable.ic_call_profile_24,
            "B",
            date2.format(DateTimeUtils.dateFormatter),
            "b"
        ))
        for(i in 0..10) {
            messageChattingArray.add(
                MessageChattingData(
                    R.drawable.ic_call_profile_24,
                    "C",
                    date2.format(DateTimeUtils.dateFormatter),
                    "c"
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
                        val dynamicButton = messageChattingList.layoutManager?.findViewByPosition(i)?.findViewById<DynamicButton>(R.id.dynamic_button)
                        if(dynamicButton != null && dynamicButton.getToggle()) {
                            dynamicButton.startTouchUpAnimation()
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
        binding.messageButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            true
        }
    }
}
