package com.sungkyul.synergy.training_space.kakao

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.kakotalk.KakaoChatCourse
import com.sungkyul.synergy.databinding.ActivityPracticeKakao2Binding
import com.sungkyul.synergy.databinding.ActivityPracticeKakao33Binding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.kakaotalk.activity.KakaoMainActivity
import com.sungkyul.synergy.learning_space.kakaotalk.adapter.ChatAdapter
import com.sungkyul.synergy.learning_space.kakaotalk.data.ChatMessage
import com.sungkyul.synergy.training_space.kakao.adapter.Chat2Adapter
import com.sungkyul.synergy.training_space.kakao.data.ChatMessage2
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PracticeKakao33Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeKakao33Binding

    private lateinit var chatAdapter: Chat2Adapter
    private val chatList = mutableListOf<ChatMessage2>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeKakao33Binding.inflate(layoutInflater)
        setContentView(binding.root)

        chatAdapter = Chat2Adapter(chatList)
        binding.recyclerView.adapter = chatAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        binding.messageEditText.setOnClickListener {
            if (binding.eduScreen.onAction("click_message_edit_text")) {

            }
        }

        binding.sharpButton.setOnClickListener {
            val message = binding.messageEditText.text.toString()
            if (message.isNotEmpty()) {
                val chatMessage = ChatMessage2("나", message, getCurrentTimestamp())
                chatAdapter.addMessage(chatMessage)  // 어댑터에 메시지 추가

                // RecyclerView의 최신 메시지로 스크롤
                binding.recyclerView.scrollToPosition(chatList.size - 1)

                // 메시지 입력 필드 비우기
                binding.messageEditText.text.clear()
            }
        }
    }

    private fun getCurrentTimestamp(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    // 키보드를 자동으로 열어주는 함수
    private fun hideKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
