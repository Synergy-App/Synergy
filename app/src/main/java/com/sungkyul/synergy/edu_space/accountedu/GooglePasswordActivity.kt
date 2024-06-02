package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.databinding.ActivityGooglePasswordBinding
import com.sungkyul.synergy.edu_courses.accountedu.GooglePasswordCourse

class GooglePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGooglePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGooglePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기화 시 비밀번호 필드를 가림
        togglePasswordVisibility(false)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GooglePasswordCourse(binding.eduScreen)

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
        binding.showPasswordCheckbox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(isChecked)
        }

        binding.googlePwNextButton.setOnClickListener {
            val password = binding.googlePwEdittext.text.toString()
            val confirmPassword = binding.googlePwCheckEdittext.text.toString()

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

        // passwordEditText의 텍스트 변경 감지
        binding.googlePwEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("pw_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })
        // confirmPasswordEditText의 텍스트 변경 감지
        binding.googlePwCheckEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("pw_check_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })
    }

    private fun togglePasswordVisibility(visible: Boolean) {
        if (visible) {
            // 체크박스 체크 시 비밀번호 및 확인 에딧창의 내용을 보여줌
            binding.googlePwEdittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.googlePwCheckEdittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            // 체크박스 해제 시 비밀번호 및 확인 에딧창의 내용을 숨김
            binding.googlePwEdittext.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.googlePwCheckEdittext.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}
