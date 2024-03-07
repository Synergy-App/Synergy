package com.sungkyul.synergy.edu_space.delivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.delivery.DlvMainActivity
import com.sungkyul.synergy.edu_space.kakaotaxi.mapActivity

class shopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

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