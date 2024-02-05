package com.sungkyul.synergy.settingedu

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivitySettingFontsizeBinding

class SettingFontSizeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SettingFontSizeActivity"
    }

    private lateinit var viewBinding: ActivitySettingFontsizeBinding
    private lateinit var pref: DefaultPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = DefaultPreferenceManager(this)

        val textSize = pref.getTextSize()
        setTheme(getAppTheme(textSize))

        viewBinding = ActivitySettingFontsizeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
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
