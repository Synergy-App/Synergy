package com.sungkyul.synergy.learning_space.basic_edu.activity

import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.dictionary_edu.data.Icon
import com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.dictionary_edu.data.IconInfo
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.GalaxyNote9
import com.sungkyul.synergy.databinding.ActivityIconDetailBinding
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.GALAXY_ON7_FRIME

class IconDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityIconDetailBinding
    private var standardSizeX: Int = 0
    private var standardSizeY: Int = 0
    private var density: Float = 0f
    private var iconList: ArrayList<Icon>? = null
    private var currentItemIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityIconDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        // 해상도와 density를 기준으로 표준 크기를 계산합니다.
        getStandardSize()

        val iconInfo = intent.getSerializableExtra("iconInfo") as IconInfo
        iconList = intent.getSerializableExtra("iconList") as ArrayList<Icon>?
        currentItemIndex = intent.getIntExtra("currentItemIndex", 0)

        // 아이콘 정보를 화면에 표시합니다.
        activityBinding.iconTv2.text = iconInfo.iconText
        activityBinding.iconDetailIv.setImageResource(iconInfo.iconImageResId)
        activityBinding.anotherIconIv.text = iconInfo.iconDescription

        // 기기별 해상도를 기준으로 글씨 크기를 조절합니다.
        when(Build.MODEL) {
            GALAXY_NOTE9 -> {
                activityBinding.iconeduTool.textSize =
                    DisplayUtils.dpToPx(this, 12.0f)//(standardSizeX / 12).toFloat()
                activityBinding.iconeduTool2.textSize =
                    DisplayUtils.dpToPx(this, 16.0f)//(standardSizeX / 20).toFloat()
                activityBinding.iconTv2.textSize =
                    DisplayUtils.dpToPx(this, 8.0f)//(standardSizeX / 12).toFloat()
                activityBinding.anotherIconIv.textSize =
                    DisplayUtils.dpToPx(this, 6.0f)//(standardSizeY / 26).toFloat()
            }
            GALAXY_ON7_FRIME -> {
                activityBinding.iconeduTool.textSize =
                    DisplayUtils.dpToPx(this, 12.0f)//(standardSizeX / 12).toFloat()
                activityBinding.iconeduTool2.textSize =
                    DisplayUtils.dpToPx(this, 9.0f)//(standardSizeX / 16).toFloat()
                activityBinding.iconTv2.textSize =
                    DisplayUtils.dpToPx(this, 10.0f)//(standardSizeX / 12).toFloat()
                activityBinding.anotherIconIv.textSize =
                    DisplayUtils.dpToPx(this, 10.0f)//(standardSizeY / 18).toFloat()
            }
        }

        // ImageView 크기 조정
        adjustImageViewSize()

        // next_nav 버튼 클릭 이벤트 처리
        val nextNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.next_nav)
        nextNavButton.setOnClickListener {
            val nextItemIndex = (currentItemIndex + 1) % (iconList?.size ?: 1)
            val nextIconInfo = iconList?.get(nextItemIndex)?.let {
                IconInfo(it.iconImage, it.iconText, it.iconDescription)
            }
            nextIconInfo?.let {
                val intent = Intent(this, IconDetailActivity::class.java)
                intent.putExtra("iconInfo", it)
                intent.putExtra("iconList", iconList)
                intent.putExtra("currentItemIndex", nextItemIndex)
                startActivity(intent)
                overridePendingTransition(0, 0) // 모션 없이 전환
                finish()
            }
        }

        // back_nav 버튼 클릭 이벤트 처리
        val backNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.back_nav)
        backNavButton.setOnClickListener {
            val prevItemIndex = if (currentItemIndex - 1 < 0) (iconList?.size ?: 1) - 1 else currentItemIndex - 1
            val prevIconInfo = iconList?.get(prevItemIndex)?.let {
                IconInfo(it.iconImage, it.iconText, it.iconDescription)
            }
            prevIconInfo?.let {
                val intent = Intent(this, IconDetailActivity::class.java)
                intent.putExtra("iconInfo", it)
                intent.putExtra("iconList", iconList)
                intent.putExtra("currentItemIndex", prevItemIndex)
                startActivity(intent)
                overridePendingTransition(0, 0) // 모션 없이 전환
                finish()
            }
        }

        // home_nav 버튼 클릭 이벤트 처리
        val homeNavButton = activityBinding.practiceNavLayout.root.findViewById<ImageView>(R.id.home_nav)
        homeNavButton.setOnClickListener {
            /*val intent = Intent(this, IconEduFragment::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)*/
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
        val params = activityBinding.iconDetailIv.layoutParams as ConstraintLayout.LayoutParams
        params.width = (newWidth * density).toInt()
        params.height = (newHeight * density).toInt()
        activityBinding.iconDetailIv.layoutParams = params

        if(Build.MODEL == GALAXY_NOTE9) {
            GalaxyNote9.setTitleSize(findViewById(R.id.iconedu_tool))
            GalaxyNote9.setSubtitleSize(findViewById(R.id.iconedu_tool_2))
            val context = this
            findViewById<View>(R.id.icon_green).layoutParams.apply {
                this.height = DisplayUtils.dpToPx(context, 175.0f).toInt()
            }
        }
    }
}