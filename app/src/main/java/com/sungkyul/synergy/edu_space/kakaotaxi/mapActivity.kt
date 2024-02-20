package com.sungkyul.synergy.edu_space.kakaotaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotaxi.TaxiMainActivity

private lateinit var staEditText: EditText
private lateinit var desEditText: EditText
private lateinit var resultTextView: TextView
class mapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

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