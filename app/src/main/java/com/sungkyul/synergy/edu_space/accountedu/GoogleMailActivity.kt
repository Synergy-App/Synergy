package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleMailBinding
import com.sungkyul.synergy.utils.edu.EduCourses

class GoogleMailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMailBinding

    private lateinit var existingMailText: TextView
    private lateinit var mailNextButton: Button
    private lateinit var newMailAddressEditText: EditText
    private lateinit var smallText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 customCourse를 지정한다.
            binding.eduScreen.course = EduCourses.googleMailCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        // 뷰 초기화
        existingMailText = findViewById(R.id.existing_mail_text)
        mailNextButton = findViewById(R.id.mail_next_button)
        newMailAddressEditText = findViewById(R.id.new_mail_address_edittext)
        smallText = findViewById(R.id.small_text)

        val radioButtonTemp3 = findViewById<RadioButton>(R.id.radio_button_temp3)

        radioButtonTemp3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showNewMailEditText()
            } else {
                hideNewMailEditText()
            }
        }

        mailNextButton.setOnClickListener {
            // GooglePasswordActivity 로 이동하는 인텐트 생성
            val nextIntent = Intent(this, GooglePasswordActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)
            nextIntent.putExtra("email", when(binding.mailRadioGroup.checkedRadioButtonId) {
                binding.radioButtonTemp1.id -> binding.radioButtonTemp1.text.toString()
                binding.radioButtonTemp2.id -> binding.radioButtonTemp2.text.toString()
                else -> binding.newMailAddressEdittext.text.toString()
            })

            startActivity(nextIntent) // 화면 전환
        }
    }


    private fun showNewMailEditText() {
        // 메일 에딧창, 작은 텍스트 표시
        newMailAddressEditText.visibility = View.VISIBLE
        smallText.visibility = View.VISIBLE

        // 기존 이메일 사용 텍스트와 다음 버튼을 메일 에딧창 아래로 이동
        moveTextAndButton()
    }

    private fun hideNewMailEditText() {
        // 메일 에딧창, 작은 텍스트 숨기기
        newMailAddressEditText.visibility = View.GONE
        smallText.visibility = View.GONE

        // 기존 위치로 이동
        resetTextAndButton()
    }

    private fun moveTextAndButton() {
        val params = newMailAddressEditText.layoutParams as ConstraintLayout.LayoutParams
        val existingMailParams = existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = mailNextButton.layoutParams as ConstraintLayout.LayoutParams
        val smallTextParams = smallText.layoutParams as ConstraintLayout.LayoutParams

        // 메일 에딧창 아래로 이동
        params.topToBottom = R.id.mail_radio_group
        newMailAddressEditText.layoutParams = params

        // 작은 텍스트 아래로 이동
        smallTextParams.topToBottom = R.id.new_mail_address_edittext
        smallText.layoutParams = smallTextParams

        // 기존 이메일 사용 텍스트와 다음 버튼도 메일 에딧창 아래로 이동
        existingMailParams.topToBottom = R.id.small_text
        existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.small_text
        mailNextButton.layoutParams = buttonParams

        // 스몰 텍스트 왼쪽으로 이동
        smallText.translationX = -120f // 이동 거리 조정

        // 스몰 텍스트와 다른 뷰 사이의 간격 조정
        smallTextParams.marginStart = 0 // 간격 조정
        existingMailParams.topMargin = 120 // 간격 조정
        buttonParams.topMargin = 120 // 간격 조정
    }


    private fun resetTextAndButton() {
        val existingMailParams = existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = mailNextButton.layoutParams as ConstraintLayout.LayoutParams

        // 기존 이메일 사용 텍스트와 다음 버튼을 초기 위치로 되돌림
        existingMailParams.topToBottom = R.id.mail_radio_group
        existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.existing_mail_text
        mailNextButton.layoutParams = buttonParams
    }
}
