package com.sungkyul.synergy.edu_space.appinstall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityInstallBinding
import com.sungkyul.synergy.utils.edu.EduCourses

class installActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스 customCourse를 지정한다.
            binding.eduScreen.course = EduCourses.installCourse(
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

        val searchQuery = intent.getStringExtra("search_query")


        //검색어 가져옴 출력까지
        val textView: TextView =findViewById(R.id.desEditText)
        textView.text=searchQuery
        val button43: Button = findViewById(R.id.button43)
        val textView175: TextView = findViewById(R.id.textView175)

        // 버튼 클릭 리스너 설정
        button43.setOnClickListener {
            // 현재 버튼의 텍스트를 가져옴
            val buttonText = button43.text.toString()

            // 버튼 텍스트에 따라 동작 수행
            button43.setOnClickListener {
                // 버튼 텍스트를 "열기"로 변경
                button43.text = "열기"
                // textView175의 문자를 "설치됨"으로 변경
                textView175.text = "설치됨"


            }
        }
    }



}