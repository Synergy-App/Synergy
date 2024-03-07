package com.sungkyul.synergy.edu_space.settingedu.activity

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivitySettingsFontBinding

class SettingFontSizeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SettingFontSizeActivity"
    }

    private lateinit var viewBinding: ActivitySettingsFontBinding
    private lateinit var pref: DefaultPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySettingsFontBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        
        pref = DefaultPreferenceManager(this)

        val textSize = pref.getTextSize()
        setTheme(getAppTheme(textSize))

        initView()

        // 교육을 진행해보자!
        // TODO(여기에 숨겨진 오류 너무 많아서 보류)
    }

    private fun initView() {
        // textsize_seekbar는 뷰 바인딩을 통해 이미 초기화되어 있음
        viewBinding.textsizeSeekbar.progress = pref.getTextSize()

        viewBinding.textsizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // do nothing
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                pref.setTextSize(seekBar.progress)
                recreate()
            }
        })
    }

    private fun getAppTheme(textSize: Int) =
        when (textSize) {
            0 -> R.style.Theme_App_0
            1 -> R.style.Theme_App_1
            2 -> R.style.Theme_App_2
            3 -> R.style.Theme_App_3
            4 -> R.style.Theme_App_4
            5 -> R.style.Theme_App_5
            6 -> R.style.Theme_App_6
            7 -> R.style.Theme_App_7

            else -> R.style.Theme_App_3 // 기본값은 3
        }
}
