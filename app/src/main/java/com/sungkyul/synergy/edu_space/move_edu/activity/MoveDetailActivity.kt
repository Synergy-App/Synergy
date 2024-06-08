package com.sungkyul.synergy.edu_space.move_edu.activity

import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.databinding.ActivityMoveDetailBinding
import com.sungkyul.synergy.edu_space.move_edu.data.MoveInfo

class MoveDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMoveDetailBinding
    private var standardSizeX: Int = 0
    private var standardSizeY: Int = 0
    private var density: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMoveDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        // 해상도와 density를 기준으로 표준 크기를 계산합니다.
        getStandardSize()


        val moveInfo = intent.getSerializableExtra("moveInfo") as MoveInfo

        activityBinding.moveTv1.text = moveInfo.moveText
        activityBinding.moveDetailIv.setImageResource(moveInfo.moveImageResId)
        activityBinding.moveTv2.text = moveInfo.moveDescription

        // 기기별 해상도를 기준으로 글씨 크기를 조절합니다.
        activityBinding.iconeduTool.textSize = (standardSizeX / 12).toFloat()
        activityBinding.iconeduTool2.textSize = (standardSizeX / 20).toFloat()
        activityBinding.searchEditText.textSize = (standardSizeX / 20).toFloat()
        activityBinding.moveTv1.textSize = (standardSizeX / 12).toFloat()
        activityBinding.moveTv2.textSize = (standardSizeY / 26).toFloat()

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
}