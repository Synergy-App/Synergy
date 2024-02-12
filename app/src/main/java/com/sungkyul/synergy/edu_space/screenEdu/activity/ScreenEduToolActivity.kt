package com.sungkyul.synergy.edu_space.screenEdu.activity


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class ScreenEduToolActivity : AppCompatActivity() {
    private var clickCount = 0 // 다음 버튼 클릭 횟수를 저장하는 변수
    private lateinit var descriptionTextView: TextView // 텍스트뷰 선언

    // 이전에 표시한 텍스트를 저장하는 리스트
    private val previousTexts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_toolbar)

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
                    1 -> "주로 스마트폰의 상태를 보여줍니다"
                    else -> " " // 클릭 2번 이상부터는 계속 같은 문구로 유지
                }

                // 현재 텍스트를 리스트에 추가
                previousTexts.add(descriptionTextView.text.toString())

                descriptionTextView.text = textToDisplay

                // 2번 클릭 시 ScreenEduLeftToolActivity로 전환
                if (clickCount == 2) {
                    startActivity(Intent(this, ScreenEduLeftToolActivity::class.java))
                    return@setOnClickListener
                }
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
