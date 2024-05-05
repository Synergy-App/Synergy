package com.sungkyul.synergy.edu_space.default_app.activity

import DefaultPhoneContactFragment
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneAddBinding
import com.sungkyul.synergy.utils.AnimUtils

class DefaultPhoneAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultPhoneAddBinding
    private var contactFragment: DefaultPhoneContactFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultPhoneAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼의 터치 애니메이션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.cancelButton)
        AnimUtils.initTouchButtonAnimation(binding.saveButton)

        // 버튼의 터치 리스너를 설정한다.
        binding.cancelButton.setOnTouchListener(onTouchButtonListener)
        binding.saveButton.setOnTouchListener(onTouchButtonListener)

        binding.phoneNameEditText.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                binding.eduScreen.onAction("phone_name_edit_text")
            }
        }
    }

    private val onTouchButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startTouchDownButtonAnimation(this, view)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startTouchUpButtonAnimation(this, view)
                if (view == binding.saveButton) {
                    //saveContact()
                } else if (view == binding.cancelButton) {
                    finish()
                }
            }
        }
        true
    }


   /* private fun saveContact() {
        val name = binding.phoneNameEditText.text.toString()
        val phoneNumber = binding.phoneNumEditText.text.toString()

        // DefaultPhoneContactFragment를 동적으로 추가한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = DefaultPhoneContactFragment()
        fragmentTransaction.add(R.id.item_layout, fragment)
        fragmentTransaction.commit()

        // 추가한 Fragment에 연락처를 전달한다.
        fragment.addContact(ContactData(R.drawable.ic_person_black_24dp, name, "휴대전화: $phoneNumber"))

        // 다음 화면으로 이동하지 않고 현재 화면을 종료한다.
        finish()
    }

*/

}
