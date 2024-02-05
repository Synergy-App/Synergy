package com.sungkyul.synergy.edu_space.settingedu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.sungkyul.synergy.edu_space.settingedu.data.SettingData
import com.sungkyul.synergy.databinding.ActivitySettingDetailBinding

/** 교육공간 속 환경설정교육 속 상세분야 액티비티 (환경설정 리사이클러뷰 아이템 누르면 나오는 액티비티) */
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



        // 디스플레이를 클릭한 경우에만 추가적인 내용을 보여줌
        if (datas.name == "디스플레이") {
            // 이미지와 텍스트 감추기
            binding.imgSetting.visibility = View.GONE
            binding.tvName.visibility = View.GONE


                // 디스플레이 설정을 보여주는 새로운 화면으로 이동하는 코드 추가
                // 예: startActivity(Intent(this, DisplaySettingsActivity::class.java))

        } else {
            // 디스플레이가 아닌 경우 이미지와 텍스트 보이기, 버튼 감추기
            Glide.with(this).load(datas.img).into(binding.imgSetting)
            binding.tvName.text = datas.name
            binding.imgSetting.visibility = View.VISIBLE
            binding.tvName.visibility = View.VISIBLE
        }
    }
}