package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class GooglePasswordActivity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var showPasswordCheckBox: CheckBox
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_password)

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
                val intent = Intent(this, GoogleGetCodeActivity::class.java)
                startActivity(intent)
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
