package com.sungkyul.synergy.learning_space.kakaotalk.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.databinding.ActivityKakaoChattingBinding
import com.sungkyul.synergy.courses.kakotalk.KakaoChatCourse
import com.sungkyul.synergy.learning_space.kakaotalk.adapter.ChatAdapter
import com.sungkyul.synergy.learning_space.kakaotalk.data.ChatMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** 카카오톡 채팅 내부 화면 */
class KakaoChattingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoChattingBinding

    private lateinit var chatAdapter: ChatAdapter
    private val chatList = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatAdapter = ChatAdapter(chatList)
        binding.recyclerView.adapter = chatAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = KakaoChatCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                /*
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                */

                val intent = Intent(binding.root.context, KakaoMainActivity::class.java)
                intent.putExtra("from", "KakaoChattingActivity")
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

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
                binding.eduScreen.onAction("click_sharp_button")

                val chatMessage = ChatMessage("나", message, getCurrentTimestamp())
                chatAdapter.addMessage(chatMessage)  // 어댑터에 메시지 추가

                // RecyclerView의 최신 메시지로 스크롤
                binding.recyclerView.scrollToPosition(chatList.size - 1)

                // 메시지 입력 필드 비우기
                binding.messageEditText.text.clear()

                // 키보드를 숨긴다.
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    binding.messageEditText.windowToken,
                    0
                )
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
