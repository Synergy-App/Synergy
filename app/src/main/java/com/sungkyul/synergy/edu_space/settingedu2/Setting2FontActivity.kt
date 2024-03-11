package com.sungkyul.synergy.edu_space.settingedu2

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivitySetting2FontBinding

class Setting2FontActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetting2FontBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetting2FontBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기 버튼 클릭 시 이벤트 처리
        binding.fontBackButton.setOnClickListener {
            // 새로운 액티비티 시작
            startActivity(Intent(this, Setting2DisplayActivity::class.java))
        }

        binding.textsizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // SeekBar 값이 변경될 때마다 메인 텍스트의 크기를 조절
                val textSize = when (progress) {
                    0 -> resources.getDimensionPixelSize(R.dimen.main_text_size_0).toFloat()
                    1 -> resources.getDimensionPixelSize(R.dimen.main_text_size_1).toFloat()
                    2 -> resources.getDimensionPixelSize(R.dimen.main_text_size_2).toFloat()
                    3 -> resources.getDimensionPixelSize(R.dimen.main_text_size_3).toFloat()
                    4 -> resources.getDimensionPixelSize(R.dimen.main_text_size_4).toFloat()
                    5 -> resources.getDimensionPixelSize(R.dimen.main_text_size_5).toFloat()
                    6 -> resources.getDimensionPixelSize(R.dimen.main_text_size_6).toFloat()
                    7 -> resources.getDimensionPixelSize(R.dimen.main_text_size_7).toFloat()
                    else -> resources.getDimensionPixelSize(R.dimen.main_text_size_default).toFloat()
                }
                binding.mainText.setTextSize(textSize)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}
