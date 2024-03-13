package com.sungkyul.synergy.edu_space.naver.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityNaverSearchResultBinding
import com.sungkyul.synergy.edu_space.naver.adapter.NaverPostAdapter
import com.sungkyul.synergy.edu_space.naver.adapter.NaverPostData
import com.sungkyul.synergy.edu_space.naver.adapter.NaverResultPostAdapter
import com.sungkyul.synergy.edu_space.naver.adapter.NaverResultPostData
import com.sungkyul.synergy.utils.AnimUtils

class NaverSearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchResultBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverSearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naverResultPostArray = ArrayList<NaverResultPostData>()
        naverResultPostArray.add(NaverResultPostData(R.drawable.usercircle, "추민영", "팀장", "프로젝트 기획, 문서 정리, UX/UI 디자인, 로고 이미지 제작, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverResultPostArray.add(NaverResultPostData(R.drawable.usercircle, "노승현", "팀원", "프로젝트 기획, ChatGPT 구현 및 개발, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverResultPostArray.add(NaverResultPostData(R.drawable.usercircle, "허수진", "대장", "프로젝트 기획, UI 구현 및 개발, 서버 생성 연동", R.drawable.sample_screenshot2))
        naverResultPostArray.add(NaverResultPostData(R.drawable.usercircle, "박연우", "팀원", "프로젝트 기획, 문서 정리, 아이디어 제시, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverResultPostArray.add(NaverResultPostData(R.drawable.usercircle, "김병윤", "팀원", "", R.drawable.sample_screenshot2))

        val searchResults = binding.searchResults
        val layoutManager = LinearLayoutManager(binding.root.context)
        searchResults.layoutManager = layoutManager
        searchResults.adapter = NaverResultPostAdapter(naverResultPostArray)

        binding.naverButton.background.alpha = 0
        binding.searchEditText.setText(intent.getStringExtra("search_words"))

        binding.naverButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.background.alpha = 32
                }
                MotionEvent.ACTION_UP -> {
                    view.background.alpha = 0

                    // 네이버의 태초 마을로 이동한다.
                    val intent = Intent(this, NaverActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)

                    view.performClick()
                }
            }
            true
        }
        binding.clearButton.setOnClickListener {
            finish()
        }
    }
}
