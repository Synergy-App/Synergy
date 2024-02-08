package com.sungkyul.synergy.edu_space.screenEdu.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class ScreenEduBottomActivity : AppCompatActivity() {
    private var clickCount = 0 // 다음 버튼 클릭 횟수를 저장하는 변수
    private lateinit var descriptionTextView: TextView // 텍스트뷰 선언

    // 이전에 표시한 텍스트를 저장하는 리스트
    private val previousTexts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_nevigation)

        // 상단바 없애기
        supportActionBar?.hide()

        // 하단바 없애기
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // 다음 버튼 클릭 시
        val nextButton = findViewById<ImageView>(R.id.screenedu_nextbtn)
        val backButton = findViewById<ImageView>(R.id.screenedu_backbtn)
        descriptionTextView = findViewById(R.id.description_text)

        // findViewById로 찾은 뷰가 null이 아닌지 확인
        if (nextButton != null && backButton != null) {
            nextButton.setOnClickListener {
                clickCount++ // 클릭 횟수 증가

                // 텍스트 변경
                val textToDisplay = when (clickCount) {
                    1 -> "이 버튼은 최근 실행 앱을 볼 수 있습니다."
                    2 -> "이 버튼은 홈 화면으로 돌아갈 수 있습니다."
                    3 -> "이 버튼은 뒤로 돌아갈 수 있습니다"
                    4 -> "상단바(상대 표시줄)을 내려주세요."
                    5 -> "원하는 동작5"
                    6 -> {
                        // 6번째 클릭일 때 다른 화면으로 이동
                        startActivity(Intent(this, ScreenEduToolActivity::class.java))
                        finish() // 현재 화면 종료
                        return@setOnClickListener
                    }
                    else -> "클릭을 계속해주세요." // 클릭 7번 이상부터는 계속 같은 문구로 유지
                }

                // 현재 텍스트를 리스트에 추가
                previousTexts.add(descriptionTextView.text.toString())

                descriptionTextView.text = textToDisplay
            }

            backButton.setOnClickListener {
                // 이전 멘트가 있을 때만 실행
                if (previousTexts.isNotEmpty()) {
                    // 리스트에서 이전 멘트를 꺼내고 텍스트뷰에 설정
                    val previousText = previousTexts.removeAt(previousTexts.size - 1)
                    descriptionTextView.text = previousText

                    // 클릭 횟수도 감소
                    clickCount--
                }
            }
        }
    }
}