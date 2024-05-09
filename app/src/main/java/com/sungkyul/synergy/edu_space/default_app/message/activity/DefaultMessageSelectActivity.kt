package com.sungkyul.synergy.edu_space.default_app.message.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageSelectBinding
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.edu_space.default_app.message.adapter.MessageContactData
import com.sungkyul.synergy.edu_space.default_app.message.adapter.MessageSelectAdapter
import com.sungkyul.synergy.edu_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.GalaxyButton
import com.sungkyul.synergy.utils.edu.EduCourses

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
            // 교육 코스 customCourse를 지정한다.
            binding.eduScreen.course = EduCourses.defaultMessageCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
        // 기존 연락처 목록 추가
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "대장님", "휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "기획자","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, ":fearful:","휴대전화: 010-1234-1234"))


        contactAdapter = MessageSelectAdapter(contactArray)
        binding.messageContactList.layoutManager = LinearLayoutManager(this)
        binding.messageContactList.adapter = contactAdapter

    }
}
