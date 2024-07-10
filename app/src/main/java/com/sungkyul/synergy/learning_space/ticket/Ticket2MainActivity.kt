package com.sungkyul.synergy.learning_space.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityTicket2MainBinding
import com.sungkyul.synergy.courses.ticket.Ticket2MainCourse

class Ticket2MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicket2MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicket2MainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_ticket2_main)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = Ticket2MainCourse(binding.eduScreen)

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

        val resButton1: Button = findViewById(R.id.button16)

        resButton1.setOnClickListener {
            val mapIntent = Intent(this, resActivity::class.java)
            startActivity(mapIntent)
        }
    }
}
