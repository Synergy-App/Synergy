package com.sungkyul.synergy.edu_space.settingedu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.settingedu2.Setting2FontActivity
import com.sungkyul.synergy.edu_space.settingedu2.Setting2MainActivity

class Setting2DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting2_display)

        // setting_display_back_button 버튼 찾기
        val settingDisplayBackButton = findViewById<ImageButton>(R.id.setting_display_back_button)

        // settingDisplayBackButton 클릭 이벤트 처리
        settingDisplayBackButton.setOnClickListener {
            // Setting2MainActivity로 이동하는 Intent 생성
            val intent = Intent(this, Setting2MainActivity::class.java)
            startActivity(intent)
        }

        // setting2_display_font_btn 버튼 찾기
        val displayFontBtn = findViewById<Button>(R.id.setting2_display_font_btn)

        // displayFontBtn 클릭 이벤트 처리
        displayFontBtn.setOnClickListener {
            // Setting2FontActivity로 이동하는 Intent 생성
            val intent = Intent(this, Setting2FontActivity::class.java)
            startActivity(intent)
        }

        // 시크바 찾기
        val brightnessSeekBar = findViewById<SeekBar>(R.id.sb_brightness)

        // 시크바 변경 이벤트 처리
        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 시스템 화면 밝기 설정하기 (progress 값으로 밝기 조절)
                val brightnessValue = progress / 100.0f // SeekBar의 max가 100이므로 비율로 환산
                val layoutParams = window.attributes
                layoutParams.screenBrightness = brightnessValue // 화면 밝기 설정
                window.attributes = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
