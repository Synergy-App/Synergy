package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageBinding
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.MessageData
import com.sungkyul.synergy.edu_space.default_app.adapter.MyMessageData
import com.sungkyul.synergy.edu_space.default_app.adapter.YourMessageData
import com.sungkyul.synergy.utils.AnimUtil
import com.sungkyul.synergy.utils.DateTimeUtils
import java.time.LocalDateTime

class DefaultMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultMessageBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dateTime1 = LocalDateTime.of(2024, 12, 1, 0, 0)
        val dateTime2 = LocalDateTime.of(2024, 1, 1, 0, 0)
        val now = LocalDateTime.now()

        val messageArray = ArrayList<MessageData>()
        messageArray.add(YourMessageData(
            R.drawable.ic_person_black_24dp,
            "님",
            "${dateTime1.format(DateTimeUtils.dateFormatter)} ${DateTimeUtils.getKoreanDayOfWeek(dateTime1)}",
            "${DateTimeUtils.getKoreanPeriod(dateTime1)} ${dateTime1.format(DateTimeUtils.timeFormatter)}"
        ))
        messageArray.add(MyMessageData(
            "!?",
            "${dateTime2.format(DateTimeUtils.dateFormatter)} ${DateTimeUtils.getKoreanDayOfWeek(dateTime2)}",
            "${DateTimeUtils.getKoreanPeriod(dateTime2)} ${dateTime2.format(DateTimeUtils.timeFormatter)}"
        ))
        messageArray.add(YourMessageData(
            R.drawable.ic_person_black_24dp,
            "딴짓 하지 말고",
            "${dateTime2.format(DateTimeUtils.dateFormatter)} ${DateTimeUtils.getKoreanDayOfWeek(dateTime2)}",
            "${DateTimeUtils.getKoreanPeriod(dateTime2)} ${dateTime2.format(DateTimeUtils.timeFormatter)}"
        ))
        messageArray.add(YourMessageData(
            R.drawable.ic_person_black_24dp,
            "일을 하세요!",
            "${now.format(DateTimeUtils.dateFormatter)} ${DateTimeUtils.getKoreanDayOfWeek(now)}",
            "${DateTimeUtils.getKoreanPeriod(now)} ${now.format(DateTimeUtils.timeFormatter)}"
        ))

        val messages = binding.messages
        messages.layoutManager = LinearLayoutManager(binding.root.context)
        messages.adapter = MessageAdapter(messageArray)

        // 버튼의 터치 애니메이션을 초기화한다.
        AnimUtil.initTouchAnimationOfButton(binding.goToTopMenuButton)
        AnimUtil.initTouchAnimationOfButton(binding.callButton)
        AnimUtil.initTouchAnimationOfButton(binding.searchButton)
        AnimUtil.initTouchAnimationOfButton(binding.conversationSettingsButton)
        AnimUtil.initTouchAnimationOfButton(binding.imageButton)
        AnimUtil.initTouchAnimationOfButton(binding.cameraButton)
        AnimUtil.initTouchAnimationOfButton(binding.plusButton)
        AnimUtil.initTouchAnimationOfButton(binding.expandButton)
        AnimUtil.initTouchAnimationOfButton(binding.emojiButton)
        AnimUtil.initTouchAnimationOfButton(binding.recordButton)
        AnimUtil.initTouchAnimationOfButton(binding.sendButton)

        // 버튼의 터치 리스너를 설정한다.
        binding.goToTopMenuButton.setOnTouchListener(onTouchGoToTopMenuButtonListener)
        binding.callButton.setOnTouchListener(onTouchCallButtonListener)
        binding.searchButton.setOnTouchListener(onTouchSearchButtonListener)
        binding.conversationSettingsButton.setOnTouchListener(onTouchConversationSettingsButtonListener)
        binding.imageButton.setOnTouchListener(onTouchImageButtonListener)
        binding.cameraButton.setOnTouchListener(onTouchCameraButtonListener)
        binding.plusButton.setOnTouchListener(onTouchPlusButtonListener)
        binding.expandButton.setOnTouchListener(onTouchExpandButtonListener)
        binding.emojiButton.setOnTouchListener(onTouchEmojiButtonListener)
        binding.recordButton.setOnTouchListener(onTouchRecordButtonListener)
        binding.sendButton.setOnTouchListener(onTouchSendButtonListener)
    }

    private val onTouchGoToTopMenuButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchCallButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchSearchButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchConversationSettingsButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchImageButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchCameraButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchPlusButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchExpandButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchEmojiButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchRecordButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }

    private val onTouchSendButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startTouchDownAnimationOfButton(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startTouchUpAnimationOfButton(this, view)
                view.performClick()
            }
        }
        true
    }
}
