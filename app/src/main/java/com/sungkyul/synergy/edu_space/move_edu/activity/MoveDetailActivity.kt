package com.sungkyul.synergy.edu_space.move_edu.activity

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityMoveDetailBinding
import com.sungkyul.synergy.edu_space.move_edu.data.Move
import com.sungkyul.synergy.edu_space.move_edu.data.MoveInfo

class MoveDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMoveDetailBinding
    private var standardSizeX: Int = 0
    private var standardSizeY: Int = 0
    private var density: Float = 0f
    private var moveList: ArrayList<Move>? = null
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMoveDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        // 해상도와 density를 기준으로 표준 크기를 계산합니다.
        getStandardSize()

        moveList = intent.getSerializableExtra("moveList") as? ArrayList<Move>
        currentIndex = intent.getIntExtra("currentIndex", 0)

        displayMoveInfo(currentIndex)

        // 기기별 해상도를 기준으로 글씨 크기를 조절합니다.
        activityBinding.iconeduTool.textSize = (standardSizeX / 12).toFloat()
        activityBinding.iconeduTool2.textSize = (standardSizeX / 20).toFloat()
        activityBinding.moveTv1.textSize = (standardSizeX / 12).toFloat()
        activityBinding.moveTv2.textSize = (standardSizeY / 26).toFloat()

        // next_nav 버튼 클릭 이벤트 처리
        val nextNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.next_nav)
        nextNavButton.setOnClickListener {
            if (moveList != null && currentIndex < moveList!!.size - 1) {
                currentIndex++
                displayMoveInfo(currentIndex)
            }
        }

        // back_nav 버튼 클릭 이벤트 처리
        val backNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.back_nav)
        backNavButton.setOnClickListener {
            if (moveList != null && currentIndex > 0) {
                currentIndex--
                displayMoveInfo(currentIndex)
            }
        }

        // home_nav 버튼 클릭 이벤트 처리
        val homeNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.home_nav)
        homeNavButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        // ImageView 크기 조정
        adjustImageViewSize()
    }

    private fun getScreenSize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize() {
        val screenSize = getScreenSize()
        density = resources.displayMetrics.density

        standardSizeX = (screenSize.x / density).toInt()
        standardSizeY = (screenSize.y / density).toInt()
    }

    private fun adjustImageViewSize() {
        val newWidth = standardSizeX / 2.7 // 기기의 가로 크기의 5.5
        val newHeight = standardSizeX / 2.7 // 기기의 가로 크기의 5.5 (세로도 동일하게 설정)

        // 레이아웃 파라미터를 ViewGroup.LayoutParams로 캐스팅
        val params = activityBinding.moveDetailIv.layoutParams as ConstraintLayout.LayoutParams
        params.width = (newWidth * density).toInt()
        params.height = (newHeight * density).toInt()
        activityBinding.moveDetailIv.layoutParams = params
    }

    private fun displayMoveInfo(index: Int) {
        moveList?.let {
            val move = it[index]
            activityBinding.moveTv1.text = move.moveText
            activityBinding.moveDetailIv.setImageResource(move.moveImage)
            activityBinding.moveTv2.text = getMoveDescription(move.moveText)
        }
    }

    private fun getMoveDescription(moveText: String): String {
        return when (moveText) {
            "터치" -> "화면에 손가락을 한번 눌렀다가 떼는 동작을 의미"
            "스와이프" -> "화면에 손가락을 대고, 손가락을 움직여 화면을 이동시키는 것을 의미"
            "꾹 누르기" -> "화면을 꾹 누르고 있는 것을 의미해요. 터치와는 달리 화면에서 손가락을 떼지 않고 누르고 있는 상태를 의미"
            "드래그" -> "화면에서 특정 부분을 손가락으로 누르고 이동시키는 것을 의미해요, 스와이프와는 달리 화면에서 항목을 선택하고 움직이는 것"
            "확대&축소" -> "화면을 확대하거나 축소하고 싶을 때 사용"
            "캡처" -> "저장하고 싶은 화면을 이미지로 남기는 것"
            else -> "상세 설명이 없습니다."
        }
    }
}
