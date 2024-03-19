// IconDetailActivity.kt

package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.data.IconInfo
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.ActivityIconDetailBinding
import com.sungkyul.synergy.databinding.FragmentIconDetailBinding

class IconDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityIconDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityIconDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val iconInfo = intent.getSerializableExtra("iconInfo") as IconInfo

        // 아이콘 정보를 화면에 표시합니다.
        activityBinding.iconTv2.text = iconInfo.iconText
        activityBinding.iconDetailIv.setImageResource(iconInfo.iconImageResId)
        activityBinding.anotherIconIv.text = iconInfo.iconDescription
    }
}