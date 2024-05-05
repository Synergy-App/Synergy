package com.sungkyul.synergy.edu_space.default_app.phone.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneAddBinding
import com.sungkyul.synergy.utils.AnimUtils

class DefaultPhoneAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultPhoneAddBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultPhoneAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 각 버튼의 터치 애니메이션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.cancelButton)
        AnimUtils.initTouchButtonAnimation(binding.saveButton)

        // 이벤트 리스너들을 추가한다.
        binding.cancelButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    //finish()
                }
            }
            true
        }
        binding.saveButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    val intent = Intent(this, DefaultPhoneActivity::class.java)
                    intent.putExtra("from", "add_contact")
                    intent.putExtra("name", binding.phoneNameEditText.text.toString())
                    intent.putExtra("num", binding.phoneNumEditText.text.toString())
                    intent.putExtra("email", binding.phoneEmailEditText.text.toString())
                    intent.putExtra("group", binding.phoneGroupEditText.text.toString())
                    startActivity(intent)
                }
            }
            true
        }
        binding.phoneNameEditText.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                binding.eduScreen.onAction("phone_name_edit_text")
            }
        }
    }
}
