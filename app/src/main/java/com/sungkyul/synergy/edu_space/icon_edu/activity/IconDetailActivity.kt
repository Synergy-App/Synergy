package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.graphics.Point
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.data.IconInfo
import com.sungkyul.synergy.databinding.ActivityIconDetailBinding

class IconDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityIconDetailBinding
    private var standardSizeX: Int = 0
    private var standardSizeY: Int = 0
    private var density: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityIconDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        // 해상도와 density를 기준으로 표준 크기를 계산합니다.
        getStandardSize()

        val iconInfo = intent.getSerializableExtra("iconInfo") as IconInfo

        // 아이콘 정보를 화면에 표시합니다.
        activityBinding.iconTv2.text = iconInfo.iconText
        activityBinding.iconDetailIv.setImageResource(iconInfo.iconImageResId)
        activityBinding.anotherIconIv.text = iconInfo.iconDescription

        // 기기별 해상도를 기준으로 글씨 크기를 조절합니다.
        activityBinding.iconeduTool.textSize = (standardSizeX / 12).toFloat()
        activityBinding.iconeduTool2.textSize = (standardSizeX / 20).toFloat()
        activityBinding.searchEditText.textSize = (standardSizeX / 20).toFloat()
        activityBinding.iconTv2.textSize = (standardSizeX / 12).toFloat()
        activityBinding.anotherIconIv.textSize = (standardSizeY / 26).toFloat()

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
        val params = activityBinding.iconDetailIv.layoutParams as ConstraintLayout.LayoutParams
        params.width = (newWidth * density).toInt()
        params.height = (newHeight * density).toInt()
        activityBinding.iconDetailIv.layoutParams = params
    }
}
