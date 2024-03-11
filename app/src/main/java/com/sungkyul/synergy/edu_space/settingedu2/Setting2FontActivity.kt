package com.sungkyul.synergy.edu_space.settingedu2

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
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
                val textSize = when (progress) {
                    0 -> resources.getDimension(R.dimen.main_text_size_0)
                    1 -> resources.getDimension(R.dimen.main_text_size_1)
                    2 -> resources.getDimension(R.dimen.main_text_size_2)
                    3 -> resources.getDimension(R.dimen.main_text_size_3)
                    4 -> resources.getDimension(R.dimen.main_text_size_4)
                    5 -> resources.getDimension(R.dimen.main_text_size_5)
                    6 -> resources.getDimension(R.dimen.main_text_size_6)
                    7 -> resources.getDimension(R.dimen.main_text_size_7)
                    else -> resources.getDimension(R.dimen.main_text_size_default)
                }
                binding.mainText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }


            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}
