package com.sungkyul.synergy.learning_space.kakaotaxi

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityMapBinding
import com.sungkyul.synergy.courses.kakaotaxi.MapCourse

private lateinit var staEditText: EditText
private lateinit var desEditText: EditText
private lateinit var resultTextView: TextView

class mapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = MapCourse(binding.eduScreen)

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

        // Intent로부터 검색어를 받아옴
        val searchQuery = intent.getStringExtra("search_query")


        //검색어 가져옴 출력까지
        val textView: TextView=findViewById(R.id.desEditText)
        textView.text=searchQuery

        staEditText = findViewById(R.id.staEditText)
        desEditText = findViewById(R.id.desEditText)


        // 엔터 키 이벤트 리스너 설정
        desEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // 엔터를 눌렀을 때 수행할 동작을 여기에 작성
                performActionOnEnter()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }




    }

    private fun performActionOnEnter() {
        // 이 함수에서 엔터를 눌렀을 때 수행할 동작을 작성
        // 예를 들어, 값이 모두 입력되었는지 확인하고 다음 동작을 수행하는 등의 로직을 추가
        val staText = staEditText.text.toString()
        val desText = desEditText.text.toString()

        if (staText.isNotEmpty() && desText.isNotEmpty()) {
            // 두 EditText에 값이 모두 입력되었을 때 수행할 동작
            // 예: 다음 화면으로 이동
            val intent = Intent(this, payActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.taxi_menu, menu)

        //사용자가 입력한 검색어를 searchQuery에 저장


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.home -> {
                val homeIntent = Intent(this, TaxiMainActivity::class.java)
                startActivity(homeIntent)
                return true
            }
            R.id.alarm ->{
                return true
            }
            R.id.my -> {
                return true
            }

            else -> return true
        }

        return super.onOptionsItemSelected(item)
    }
}
class SearchResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)


    }
}