package com.sungkyul.synergy.edu_space.naver.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityNaverSearchResultBinding
import com.sungkyul.synergy.edu_space.naver.adapter.NaverPostAdapter
import com.sungkyul.synergy.edu_space.naver.adapter.NaverPostData
import com.sungkyul.synergy.edu_space.naver.adapter.NaverResultPostAdapter
import com.sungkyul.synergy.edu_space.naver.adapter.NaverResultPostData

class NaverSearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchResultBinding

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

        binding.searchEditText.setText(intent.getStringExtra("search_words"))

        binding.clearButton.setOnClickListener {
            finish()
        }
    }
}
