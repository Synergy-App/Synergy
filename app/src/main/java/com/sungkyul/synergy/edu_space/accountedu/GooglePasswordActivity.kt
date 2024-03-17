package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleMailBinding
import com.sungkyul.synergy.databinding.ActivityGooglePasswordBinding
import com.sungkyul.synergy.utils.EduCourses

class GooglePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGooglePasswordBinding

    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var showPasswordCheckBox: CheckBox
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGooglePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 customCourse를 지정한다.
            binding.eduScreen.course = EduCourses.googlePasswordCourse(
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

        passwordEditText = findViewById(R.id.google_pw_edittext)
        confirmPasswordEditText = findViewById(R.id.google_pw_check_edittext)
        showPasswordCheckBox = findViewById(R.id.show_password_checkbox)
        nextButton = findViewById(R.id.google_pw_next_button)

        // 비밀번호 및 확인 에딧창의 내용을 숨김
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        confirmPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()

        // 체크박스 클릭 리스너 설정
        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(isChecked)
        }

        nextButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                // 비밀번호 일치 시 다음 화면으로 이동
                val nextIntent = Intent(this, GoogleGetCodeActivity::class.java)

                // 값을 전달한다.
                nextIntent.putExtras(intent)
                nextIntent.putExtra("password", binding.googlePwEdittext.text.toString())

                startActivity(nextIntent)
            } else {
                // 비밀번호 불일치
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun togglePasswordVisibility(visible: Boolean) {
        if (visible) {
            // 체크박스 체크 시 비밀번호 및 확인 에딧창의 내용을 보여줌
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            confirmPasswordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            // 체크박스 해제 시 비밀번호 및 확인 에딧창의 내용을 숨김
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            confirmPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}
