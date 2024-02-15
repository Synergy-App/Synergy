package com.sungkyul.synergy.edu_space.default_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityNaverBinding

class NaverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naverPostArray = ArrayList<NaverPostData>()
        naverPostArray.add(NaverPostData(R.drawable.usercircle, "추민영", "1일 전", "팀장", "프로젝트 기획, 문서 정리, UX/UI 디자인, 로고 이미지 제작, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverPostArray.add(NaverPostData(R.drawable.usercircle, "노승현", "2일 전", "팀원", "프로젝트 기획, ChatGPT 구현 및 개발, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverPostArray.add(NaverPostData(R.drawable.usercircle, "허수진", "3일 전", "대장", "프로젝트 기획, UI 구현 및 개발, 서버 생성 연동", R.drawable.sample_screenshot2))
        naverPostArray.add(NaverPostData(R.drawable.usercircle, "박연우", "4일 전", "팀원", "프로젝트 기획, 문서 정리, 아이디어 제시, UI 구현 및 개발", R.drawable.sample_screenshot2))
        naverPostArray.add(NaverPostData(R.drawable.usercircle, "김병윤", "5일 전", "팀원", "", R.drawable.sample_screenshot2))

        val naverPostList = binding.naverPostList
        val layoutManager = LinearLayoutManager(binding.root.context)
        naverPostList.layoutManager = layoutManager
        naverPostList.adapter = NaverPostAdapter(naverPostArray)
        // TODO: height를 어떻게 계산할지 고민해보기
        naverPostList.layoutParams.height = 2000*layoutManager.itemCount // 아이템들의 높이에 맞게 네이버 포스트 리스트의 높이를 정한다.
        Log.i("all_height", "${naverPostList.layoutParams.height}")

        // 이벤트 리스너 설정
        binding.searchBar.setOnTouchListener(onTouchSearchBarListener)
    }

    // 검색 바의 터치 이벤트 리스너
    private val onTouchSearchBarListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> {
                // 네이버 검색 뷰로 이동
                val intent = Intent(this, NaverSearchActivity::class.java)
                startActivity(intent)

                view.performClick()
            }
        }
        true
    }
}
