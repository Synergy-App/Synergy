package com.sungkyul.synergy.edu_space.icon_edu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.adapter.IconEduAdapter
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.ActivityIconEduBinding
import com.sungkyul.synergy.edu_space.naver.adapter.NaverAutocompleteAdapter
import com.sungkyul.synergy.edu_space.naver.adapter.NaverAutocompleteData
import com.sungkyul.synergy.utils.TextUtils

/** 교육공간 속 아이콘 설명 액티비티 */
class IconEduActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIconEduBinding
    private lateinit var iconAdapter: IconEduAdapter // IconEduAdapter에 해당하는 부분입니다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.iconRv // XML에서 정의한 RecyclerView의 ID를 가져옵니다.

        // RecyclerView에 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager 또는 필요한 레이아웃 매니저를 사용합니다.

        val iconSearchDict = listOf(
            Pair(R.drawable.ic_sound_black, "신호 없음"),
            Pair(R.drawable.ic_sound_black, "데이터 모바일"),
            Pair(R.drawable.ic_sound_black, "와이파이"),
            Pair(R.drawable.ic_sound_black, "블루투스"),
            Pair(R.drawable.ic_sound_black, "GPS"),
            Pair(R.drawable.ic_sound_black, "음성 전화"),
            Pair(R.drawable.ic_sound_black, "부재중 전화"),
            Pair(R.drawable.ic_sound_black, "문자"),
            Pair(R.drawable.ic_sound_black, "알림"),
            Pair(R.drawable.ic_sound_black, "무음 모드"),
            Pair(R.drawable.ic_sound_black, "진동 모드"),
            Pair(R.drawable.ic_sound_black, "비행기 탑승 모드"),
            Pair(R.drawable.ic_sound_black, "배터리 양 표시")
        )

        val iconList = ArrayList<Icon>()
        for(i in iconSearchDict) {
            iconList.add(Icon(i.first, i.second))
        }

        iconAdapter = IconEduAdapter(this, iconList) // 여기에 생성한 아이템 리스트를 넣어줍니다.
        recyclerView.adapter = iconAdapter

        binding.searchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapter = recyclerView.adapter as IconEduAdapter

                // 모든 아이템들을 삭제한다.
                adapter.notifyItemRangeRemoved(0, iconList.size)

                // 자동 완성
                iconList.clear()
                if(s.toString().isNotEmpty()) {
                    for (i in iconSearchDict.filter { it.second.contains(s.toString()) }) {
                        iconList.add(Icon(i.first, i.second))
                    }
                } else {
                    // 검색 창이 비어 있으면, 모든 아이템들을 채운다.
                    for(i in iconSearchDict) {
                        iconList.add(Icon(i.first, i.second))
                    }
                }

                // 아이템들을 추가한다.
                adapter.notifyItemRangeInserted(0, iconList.size)
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}