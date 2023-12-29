package com.sungkyul.synergy.settingedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.sungkyul.synergy.data.SettingData
import com.sungkyul.synergy.databinding.ActivitySettingDetailBinding
import android.widget.Button
import com.sungkyul.synergy.R

/** 교육공간 속 환경설정교육 속 상세분야 액티비티 */
class SettingDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingDetailBinding
    private lateinit var datas: SettingData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datas = intent.getSerializableExtra("data") as SettingData

        Glide.with(this).load(datas.img).into(binding.imgSetting)
        binding.tvName.text = datas.name



        // XML에서 정의한 버튼을 가져오는 코드
        val displayButton = findViewById<Button>(R.id.btn_setting_display)

        // 만약 데이터의 이름이 "디스플레이"인 경우에만 버튼을 보여줌
        if (datas.name == "디스플레이") {
            // 클릭 이벤트 처리
            displayButton.setOnClickListener {
                // 디스플레이 설정을 보여주는 새로운 화면으로 이동하는 코드 추가
                // 예: startActivity(Intent(this, DisplaySettingsActivity::class.java))
            }

            // 버튼을 보이도록 설정
            displayButton.visibility = View.VISIBLE
        } else {
            // "디스플레이"가 아닌 경우 버튼을 숨김
            displayButton.visibility = View.GONE
        }

    }
}