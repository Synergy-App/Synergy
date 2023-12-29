package com.sungkyul.synergy.settingedu

import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.adapter.SettingEduAdapter
import com.sungkyul.synergy.data.SettingData

/** 교육공간 속 환경설정교육 액티비티 */
class VerticalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() { //RecyclerView의 ItemDecoration()을 이용하여 Item의 간격을 조정

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}

class HorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() { // RecyclerView의 ItemDecoration()을 이용하여 Item의 간격을 조정

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = divHeight
        outRect.right = divHeight
    }
}

class SettingEduActivity : AppCompatActivity() {
    lateinit var settingAdapter: SettingEduAdapter
    val datas = mutableListOf<SettingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initRecycler()
    }
    private fun initRecycler() {
        val rv_function: RecyclerView = findViewById(R.id.rv_function)
        settingAdapter = SettingEduAdapter(this)

        rv_function.addItemDecoration(VerticalItemDecorator(20)) //RecyclerView에 ItemDecorator 적용
        rv_function.addItemDecoration(HorizontalItemDecorator(10))

        rv_function.adapter = settingAdapter
        rv_function.layoutManager = LinearLayoutManager(this)


        datas.apply {
            add(SettingData(img = R.drawable.wifi, name = "연결", explain = "Wi-Fi, 블루투스, 비행기 탑승 모드, 데이터 사용"))
            add(SettingData(img = R.drawable.soundimg, name = "소리 및 진동", explain = "소리 모드, 벨소리, 음량"))
            add(SettingData(img = R.drawable.message, name = "알림", explain = "앱 알림, 상태표시줄, 방해 금지"))
            add(SettingData(img = R.drawable.display, name = "디스플레이", explain = "밝기, 블루라이트 필터, 홈 화면"))
            add(SettingData(img = R.drawable.backgroundimg, name = "배경화면", explain = "홈 배경화면, 잠금화면 배경"))
            add(SettingData(img = R.drawable.theme, name = "테마", explain = "테마, 배경화면, 아이콘"))
            add(SettingData(img = R.drawable.lock, name = "잠금화면", explain = "화면 잠금 방식, Always On Display, 시계 스타일"))

            settingAdapter.datas = datas
            settingAdapter.notifyDataSetChanged()

        }
    }
}