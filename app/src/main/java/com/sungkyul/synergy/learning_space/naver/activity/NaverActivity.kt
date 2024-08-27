package com.sungkyul.synergy.learning_space.naver.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse2
import com.sungkyul.synergy.courses.naver.NaverCourse
import com.sungkyul.synergy.courses.naver.NaverFromNaverSearchInfoCourse
import com.sungkyul.synergy.databinding.ActivityNaverBinding
import com.sungkyul.synergy.learning_space.EduCompletionActivity
import com.sungkyul.synergy.learning_space.naver.adapter.NaverPostAdapter
import com.sungkyul.synergy.learning_space.naver.adapter.NaverPostData
import com.sungkyul.synergy.learning_space.screen_layout.ScreenHomeActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.edu.EduScreen

class NaverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverBinding
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
            if(intent.getStringExtra("from") == "NaverFirstActivity") {
                binding.eduScreen.course = NaverCourse(binding.eduScreen)
            } else if(intent.getStringExtra("from") == "ScreenMenuActivity3") {
                binding.eduScreen.course = NaverFromScreenHomeCourse2(binding.eduScreen)
            } else if(intent.getStringExtra("from") == "NaverSearchInfoActivity") {
                binding.eduScreen.course = NaverFromNaverSearchInfoCourse(binding.eduScreen)
            }
            else {
                binding.eduScreen.course = NaverFromScreenHomeCourse(binding.eduScreen)
            }

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                if(binding.eduScreen.course is NaverFromScreenHomeCourse2) {
                    val intent = Intent(this, EduCompletionActivity::class.java)
                    intent.putExtra("course", "screen_layout")
                    startActivity(intent)
                } else if(binding.eduScreen.course is NaverFromNaverSearchInfoCourse) {
                    val intent = Intent(this, EduCompletionActivity::class.java)
                    intent.putExtra("course", "naver")
                    startActivity(intent)
                }
                else {
                    EduScreen.toTop(this, MainActivity::class.java)
                }
            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        hideSystemUI()

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
        naverPostList.layoutParams.height = 1500*layoutManager.itemCount // 아이템들의 높이에 맞게 네이버 포스트 리스트의 높이를 정한다.
        Log.i("all_height", "${naverPostList.layoutParams.height}")

        // 이벤트 리스너 설정
        binding.searchBar.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_UP -> {
                    if(binding.eduScreen.onAction("click_naver_search_view") && intent.getStringExtra("from")=="NaverFirstActivity") {
                        // 네이버 검색 뷰로 이동
                        //val intent = Intent(this, NaverSearchActivity::class.java)

                        // 된장찌개로 이동
                        val intent = Intent(this, NaverSearchInfoActivity::class.java)
                        startActivity(intent)
                    }

                    view.performClick()
                }
            }
            true
        }
        binding.shoppingButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownSpringButtonAnimation(view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpSpringButtonAnimation(view)

                    view.performClick()
                }
            }
            true
        }
        binding.homeButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownSpringButtonAnimation(view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpSpringButtonAnimation(view)

                    view.performClick()
                }
            }
            true
        }
        binding.contentsButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownSpringButtonAnimation(view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpSpringButtonAnimation(view)

                    view.performClick()
                }
            }
            true
        }
        binding.clipButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownSpringButtonAnimation(view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpSpringButtonAnimation(view)

                    view.performClick()
                }
            }
            true
        }



        binding.transparentView2.setOnClickListener {
            if(binding.eduScreen.onAction("click_main")) {
                val intent = Intent(this, ScreenHomeActivity::class.java)
                intent.putExtra("from", "NaverActivity")
                startActivity(intent)
            }
        }

        binding.transparentView3.setOnClickListener {
            if(binding.eduScreen.onAction("click_back")) {
                /*val intent = Intent(this, ScreenHomeActivity::class.java)
                intent.putExtra("from", "NaverActivity2")
                startActivity(intent)*/
                binding.largeAd2.visibility = View.INVISIBLE
            }
        }

        binding.ad2.setOnClickListener {
            if(binding.eduScreen.onAction("click_ad2")) {
                binding.largeAd2.visibility = View.VISIBLE
            }
        }
    }



    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}
