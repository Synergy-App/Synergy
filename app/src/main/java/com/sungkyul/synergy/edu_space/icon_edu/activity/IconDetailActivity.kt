// IconDetailActivity.kt

package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.data.IconInfo
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.ActivityIconDetailBinding
import com.sungkyul.synergy.databinding.IconDetailEduBinding

class IconDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityIconDetailBinding
    private lateinit var iconDetailBinding: IconDetailEduBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityIconDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        // icon_detail_edu.xml에 대한 바인딩 설정
        iconDetailBinding = IconDetailEduBinding.inflate(layoutInflater)

        val iconInfo = intent.getSerializableExtra("iconInfo") as IconInfo

        // 가져온 정보를 화면에 표시합니다.
        iconDetailBinding.iconTv2.text = iconInfo.iconText
        iconDetailBinding.iconDetailIv.setImageResource(iconInfo.iconImageResId)
        iconDetailBinding.anotherIconIv.text = iconInfo.iconDescription

    }
}


