package com.sungkyul.synergy.learning_space.delivery

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDlvMainBinding
import com.sungkyul.synergy.courses.delivery.DlvMainCourse

class DlvMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDlvMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDlvMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = DlvMainCourse(binding.eduScreen)

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

        //검색창에 목적지 입력시 가게목록으로 넘어감(검색어 intent로 전달)
        val searchView: SearchView = findViewById(R.id.searchView2)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val intent = Intent(this@DlvMainActivity, shopActivity::class.java)
                    intent.putExtra("search_query", query)
                    startActivity(intent)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어가 변경될 때 실행하고 싶은 동작을 여기에 추가하세요.
                return true
            }
        })

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.taxi_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
    }
}