package com.sungkyul.synergy.edu_space.default_app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.sungkyul.synergy.R

class DefaultAppBarActivity : AppCompatActivity() {

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var imageView45: ImageView
    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var linearLayout2: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_app_bar) // 이 부분에서 액티비티의 레이아웃을 설정합니다.

        // XML에서 정의한 뷰들을 참조합니다.
        appBarLayout = findViewById(R.id.appBarLayout)
        imageView45 = findViewById(R.id.imageView45)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton2 = findViewById(R.id.imageButton2)
        imageButton1 = findViewById(R.id.imageButton1)
        linearLayout2 = findViewById(R.id.linearLayout2)






        // 이미지 버튼에 클릭 리스너를 설정합니다.
        imageButton1.setOnClickListener {//메뉴버튼
            appBarLayout.setExpanded(false)
            // linearLayout2를 맨 앞으로 가져옵니다.
            linearLayout2.bringToFront()
            appBarLayout.requestLayout()

            // imageView45의 이미지를 변경합니다. 원하는 이미지 리소스 ID를 넣어주세요.
            imageView45.setImageResource(R.drawable.taskmgr)

            imageView45.bringToFront()


            linearLayout2.bringToFront()


        }

        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
            val intent = Intent(applicationContext, DefaultAppBarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // 모든 스택에 쌓인 액티비티 제거
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
                val intent = Intent(applicationContext, DefaultAppBarActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // 모든 스택에 쌓인 액티비티 제거
                startActivity(intent)
            }

    }

}
