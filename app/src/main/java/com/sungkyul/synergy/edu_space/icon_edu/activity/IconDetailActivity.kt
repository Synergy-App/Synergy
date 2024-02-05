// IconDetailActivity.kt

package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.data.IconInfo
import com.sungkyul.synergy.databinding.ActivityIconDetailBinding

class IconDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIconDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iconInfo = intent.getSerializableExtra("iconInfo") as IconInfo


        // 받아온 아이콘 정보로 레이아웃 업데이트
        iconInfo?.let {
            binding.iconImageView.setImageResource(it.iconImageResId)
            binding.iconTextView.text = it.iconText
            binding.iconDescriptionView.text = it.iconDescription
        }
    }
}
