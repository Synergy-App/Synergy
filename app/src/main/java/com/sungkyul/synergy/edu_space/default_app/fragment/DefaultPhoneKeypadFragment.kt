package com.sungkyul.synergy.edu_space.default_app.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentDefaultPhoneKeypadBinding
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_SCALE
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_SCALE
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_SCALE
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultPhoneCallActivity
import com.sungkyul.synergy.util.AnimUtil
import com.sungkyul.synergy.util.EduListener
import com.sungkyul.synergy.util.TextUtil

class DefaultPhoneKeypadFragment(private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentDefaultPhoneKeypadBinding
    private var secondaryButtonsIsEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultPhoneKeypadBinding.inflate(inflater, container, false)

        // 키 버튼 리스트
        val keyButtons = listOf(
            binding.key1,
            binding.key2,
            binding.key3,
            binding.key4,
            binding.key5,
            binding.key6,
            binding.key7,
            binding.key8,
            binding.key9,
            binding.keyStar,
            binding.key0,
            binding.keySharp
        )

        // 그 외 버튼 리스트
        val secondaryButtons = listOf(
            binding.contactAddition,
            binding.videoCall,
            binding.delete
        )

        // 키 버튼의 배경 알파 값 초기화, 터치 이벤트 추가
        for(i in keyButtons) {
            i.background.alpha = TOUCH_UP_ALPHA
            i.setOnTouchListener(onTouchKeyListener)
        }
        // 그 외 버튼의 배경 알파 값 초기화
        for(i in secondaryButtons) {
            i.background.alpha = TOUCH_UP_ALPHA
        }
        binding.call.background.alpha = TOUCH_UP_ALPHA

        binding.contactAddition.setOnTouchListener(onTouchContactAdditionListener)
        binding.videoCall.setOnTouchListener(onTouchVideoCallListener)
        binding.call.setOnTouchListener(onTouchCallListener)
        binding.delete.setOnTouchListener(onTouchDeleteListener)
        binding.delete.setOnLongClickListener(onLongClickDeleteListener)
        binding.phoneNumText.addTextChangedListener(phoneNumTextWatcher)

        binding.phoneNumText.keyListener = null // 키 버튼을 눌러도 키보드가 안 뜨게 한다.

        // 연락처 추가, 영상 통화, 지우기 버튼을 숨긴다.
        binding.contactAddition.visibility = View.INVISIBLE
        binding.videoCall.isEnabled = false
        binding.videoCall.drawable.alpha = 0
        binding.delete.isEnabled = false
        binding.delete.drawable.alpha = 0

        return binding.root
    }

    // 연락처 추가 버튼의 터치 이벤트 리스너
    private val onTouchContactAdditionListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                showContactAdditionDialog()
                view.performClick()
            }
        }
        true
    }

    // 키 버튼의 터치 이벤트 리스너
    private val onTouchKeyListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                AnimUtil.startScaleAnimation(view, TOUCH_DURATION_SCALE, TOUCH_UP_SCALE, TOUCH_DOWN_SCALE)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                TextUtil.extendText(binding.phoneNumText, (view as Button).text.toString())

                eduListener.onAction("click_key_button", view.text.toString())

                // 번호 입력 란이 비어 있지 않으면, '연락처 추가, 영상 통화, 지우기' 버튼이 나타난다.
                if(binding.phoneNumText.text.toString().isNotEmpty() && !secondaryButtonsIsEnabled) {
                    setSecondaryButtonsVisibility(true, 0, 255)
                    secondaryButtonsIsEnabled = true
                }

                view.performClick()
            }
        }
        true
    }

    // 영상 통화 버튼의 터치 이벤트 리스너
    private val onTouchVideoCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 통화 버튼의 터치 이벤트 리스너
    private val onTouchCallListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                if(binding.phoneNumText.text.toString().isNotEmpty()) {
                    // 전화 화면으로 이동
                    val intent = Intent(requireContext(), DefaultPhoneCallActivity::class.java)
                    intent.putExtra("phone_num", binding.phoneNumText.text.toString())
                    startActivity(intent)
                }

                view.performClick()
            }
        }
        true
    }

    // 지우기 버튼의 터치 이벤트 리스너
    private val onTouchDeleteListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                TextUtil.popText(binding.phoneNumText)

                // 번호 입력 란이 비어 있으면, '연락처 추가, 영상 통화, 지우기' 버튼이 사라진다.
                if(binding.phoneNumText.text.toString().isEmpty() && secondaryButtonsIsEnabled) {
                    initSecondaryButtonsBackground()
                    setSecondaryButtonsVisibility(false, 255, 0)
                    secondaryButtonsIsEnabled = false
                }

                view.performClick()
            }
        }
        false // 이벤트가 소비되지 않아야 OnLongClickListener가 호출될 수 있다.
    }

    // 지우기 버튼의 롱 클릭 이벤트 리스너
    private val onLongClickDeleteListener = View.OnLongClickListener {
        TextUtil.clearText(binding.phoneNumText)

        // 번호 입력 란이 비어 있으면, '연락처 추가, 영상 통화, 지우기' 버튼이 사라진다.
        if(binding.phoneNumText.text.toString().isEmpty() && secondaryButtonsIsEnabled) {
            initSecondaryButtonsBackground()
            setSecondaryButtonsVisibility(false, 255, 0)
            secondaryButtonsIsEnabled = false
        }
        
        true
    }

    // 전화번호 텍스트 Watcher
    private val phoneNumTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.i("PhoneNumText", s.toString())
            eduListener.onAction("phone_num_text_changed", s.toString())
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    // 연락처 추가 다이얼로그를 보여준다.
    private fun showContactAdditionDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("연락처에 추가")
            .setItems(arrayOf("새 연락처 등록", "기존 연락처 업데이트")) { dialog, which ->
                when(which) {
                    0 -> Toast.makeText(requireContext(), "새 연락처 등록", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(requireContext(), "기존 연락처 업데이트", Toast.LENGTH_SHORT).show()
                }
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun setSecondaryButtonsVisibility(enabled: Boolean, startAlpha: Int, endAlpha: Int) {
        if(enabled) binding.contactAddition.visibility = View.VISIBLE
        else binding.contactAddition.visibility = View.INVISIBLE

        binding.videoCall.isEnabled = enabled
        AnimUtil.startAlphaAnimation(binding.videoCall.drawable, TOUCH_DURATION_ALPHA, startAlpha, endAlpha)

        binding.delete.isEnabled = enabled
        AnimUtil.startAlphaAnimation(binding.delete.drawable, TOUCH_DURATION_ALPHA, startAlpha, endAlpha)
    }

    private fun initSecondaryButtonsBackground() {
        binding.contactAddition.background.alpha = TOUCH_UP_ALPHA
        binding.videoCall.background.alpha = TOUCH_UP_ALPHA
        binding.delete.background.alpha = TOUCH_UP_ALPHA
    }
}
