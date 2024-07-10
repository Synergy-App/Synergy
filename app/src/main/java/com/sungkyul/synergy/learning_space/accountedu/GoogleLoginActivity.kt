package com.sungkyul.synergy.learning_space.accountedu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleLoginBinding
import com.sungkyul.synergy.courses.accountedu.GoogleLoginCourse

class GoogleLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GoogleLoginCourse(binding.eduScreen)

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

        val textView = findViewById<TextView>(R.id.guest_code_text)
        val textData = textView.text.toString()

        val grayColor = Color.parseColor("#808080") // 회색
        val blueColor = Color.parseColor("#1B76EB") // 파란색

        // SpannableStringBuilder를 사용하여 텍스트의 일부분의 색상을 변경합니다.
        val builder = SpannableStringBuilder(textData)

        // "게스트 코드 사용 방법 자세히 알아보기" 부분을 파란색으로 변경
        val startIndex = textData.indexOf("게스트 코드 사용 방법 자세히 알아보기")
        val endIndex = startIndex + "게스트 코드 사용 방법 자세히 알아보기".length
        builder.setSpan(
            ForegroundColorSpan(blueColor),
            startIndex,
            endIndex,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 나머지 텍스트를 회색으로 변경
        builder.setSpan(
            ForegroundColorSpan(grayColor),
            0,
            startIndex,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 변경된 Spannable 문자열을 TextView에 설정합니다.
        textView.text = builder

        // "계정 만들기" 버튼을 찾습니다.
        val createAccountButton = findViewById<Button>(R.id.create_account_button)

        // "계정 만들기" 버튼에 클릭 리스너를 추가합니다.
        createAccountButton.setOnClickListener {
            // 다이얼로그를 표시합니다.
            showAccountTypeDialog()
        }
    }

    // 다이얼로그 표시 함수
    private fun showAccountTypeDialog() {
        // 다이얼로그를 생성하고 다이얼로그의 레이아웃을 설정합니다.
        val dialogView = layoutInflater.inflate(R.layout.activity_google_dialog, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // "개인용" 버튼을 찾습니다.
        val personalButton = dialogView.findViewById<Button>(R.id.btn_personal)

        // "개인용" 버튼에 클릭 리스너를 추가합니다.
        personalButton.setOnClickListener {
            // 화면을 이동시킵니다. (이동할 화면의 액티비티를 여기에 지정합니다.)
            val intent = Intent(this, GoogleMakeActivity::class.java)
            startActivity(intent)

            // 다이얼로그를 닫습니다.
            dialog.dismiss()
        }


        // 다이얼로그를 화면에 표시합니다.
        dialog.show()

        // 다이얼로그의 위치를 조정합니다.
        val layoutParams = dialog.window?.attributes
        layoutParams?.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM // 중앙 아래로 정렬
        layoutParams?.y = 340 // 아래로부터의 거리를 조절합니다. 적절한 값을 설정해주세요.
        dialog.window?.attributes = layoutParams
    }
}