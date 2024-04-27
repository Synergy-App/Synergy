
package com.sungkyul.synergy.edu_space.default_app.activity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class DefaultAppBarActivity : AppCompatActivity() {

    private lateinit var imageView45: ImageView
    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var linearLayout2: LinearLayout // linearLayout2 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_app_bar)

        // XML에서 정의한 뷰들을 참조합니다.
        imageView45 = findViewById(R.id.imageView45)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton2 = findViewById(R.id.imageButton2)
        imageButton1 = findViewById(R.id.imageButton1)
        linearLayout2 = findViewById(R.id.linearLayout2) // linearLayout2 추가

        // 이미지 버튼에 클릭 리스너를 설정합니다.
        imageButton1.setOnClickListener {//메뉴버튼
            // linearLayout2를 맨 앞으로 가져옵니다.
            linearLayout2.bringToFront()

            // imageView45의 이미지를 변경합니다. 원하는 이미지 리소스 ID를 넣어주세요.
            imageView45.setImageResource(R.drawable.taskmgr)

            imageView45.bringToFront()

            linearLayout2.bringToFront()


        }

        imageButton2.setOnClickListener {//홈버튼


        }

        imageButton3.setOnClickListener {//뒤로가기버튼


        }
    }

}