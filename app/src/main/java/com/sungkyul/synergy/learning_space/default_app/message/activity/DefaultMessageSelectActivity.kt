package com.sungkyul.synergy.learning_space.default_app.message.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageSelectBinding
import com.sungkyul.synergy.courses.default_app.message.DefaultMessageCourse1
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageContactData
import com.sungkyul.synergy.learning_space.default_app.message.adapter.MessageSelectAdapter

class DefaultMessageSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultMessageSelectBinding
    private lateinit var contactAdapter: MessageSelectAdapter
    private val contactArray = ArrayList<MessageContactData>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMessageSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = DefaultMessageCourse1(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // DefaultAppActivity로 되돌아 간다.
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
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
        // 기존 연락처 목록 추가
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "대장님", "휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "기획자","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, ":fearful:","휴대전화: 010-1234-1234"))



        contactAdapter = MessageSelectAdapter(contactArray, binding.eduScreen, this)
        binding.messageContactList.layoutManager = LinearLayoutManager(this)
        binding.messageContactList.adapter = contactAdapter

    }
}
