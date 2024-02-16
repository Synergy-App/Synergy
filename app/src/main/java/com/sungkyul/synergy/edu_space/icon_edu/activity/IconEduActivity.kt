package com.sungkyul.synergy.edu_space.icon_edu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.adapter.IconEduAdapter
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.ActivityIconEduBinding

/**교육공간 속 아이콘 설명 액티비티*/
private lateinit var binding: ActivityIconEduBinding

class IconEduActivity : AppCompatActivity() {
    private lateinit var iconAdapter: IconEduAdapter // IconEduAdapter에 해당하는 부분입니다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val recyclerView: RecyclerView = binding.iconRv // XML에서 정의한 RecyclerView의 ID를 가져옵니다.

        // RecyclerView에 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager 또는 필요한 레이아웃 매니저를 사용합니다.


            // 데이터를 생성하거나 가져와서 리스트를 만듭니다. 여기서는 예시로 더미 데이터를 만듭니다.
        val iconList = arrayListOf(
            Icon(R.drawable.sound, "음량 조절"),
            Icon(R.drawable.sound, "와이파이"),
            Icon(R.drawable.sound, "손전등"),
            Icon(R.drawable.sound, "블루투스"),
            Icon(R.drawable.sound, "비행기모드"),
            Icon(R.drawable.sound, "절전모드"),
            Icon(R.drawable.sound, "데이터 모바일"),
            Icon(R.drawable.sound, "화면 녹화"),
            Icon(R.drawable.sound, "다크모드"),
            Icon(R.drawable.sound, "빅스비 루틴"),
            // 예시임. 변경해야됨.
        )
        // RecyclerView Adapter 생성 및 연결
        iconAdapter = IconEduAdapter(this, iconList) // 여기에 생성한 아이템 리스트를 넣어줍니다.
        recyclerView.adapter = iconAdapter*/
    }
}