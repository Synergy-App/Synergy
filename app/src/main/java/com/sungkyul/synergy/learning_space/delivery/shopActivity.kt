package com.sungkyul.synergy.learning_space.delivery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityShopBinding
import com.sungkyul.synergy.courses.delivery.ShopCourse

class shopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_shop)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = ShopCourse(binding.eduScreen)

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

        val shopButton: Button = findViewById(R.id.button4)

        shopButton.setOnClickListener {
            val mapIntent = Intent(this, foodmenuActivity::class.java)
            startActivity(mapIntent)
        }

        // Intent로부터 검색어를 받아옴
        val searchQuery = intent.getStringExtra("search_query")


        //검색어 가져옴 출력까지
        val textView: TextView =findViewById(R.id.shopEditText)
        textView.text=searchQuery

    }
}