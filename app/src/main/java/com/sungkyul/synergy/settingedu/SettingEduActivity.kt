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

// RecyclerView의 Item 간격 조정을 위한 ItemDecoration 클래스 정의
class VerticalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets 메서드를 재정의하여 Item 간격 설정
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 상하 간격 설정
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}

// RecyclerView의 Item 간격 조정을 위한 ItemDecoration 클래스 정의
class HorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets 메서드를 재정의하여 Item 간격 설정
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 좌우 간격 설정
        outRect.left = divHeight
        outRect.right = divHeight
    }
}

// AppCompatActivity를 상속받는 SettingEduActivity 클래스 정의
class SettingEduActivity : AppCompatActivity() {

    // SettingEduAdapter 및 데이터 리스트를 초기화할 변수 선언
    lateinit var settingAdapter: SettingEduAdapter
    val datas = mutableListOf<SettingData>()

    // 액티비티가 생성될 때 호출되는 onCreate 메서드 재정의
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃 설정
        setContentView(R.layout.activity_setting)
        // RecyclerView 초기화 함수 호출
        initRecycler()
    }

    // RecyclerView를 초기화하고 설정하는 함수
    private fun initRecycler() {
        // RecyclerView 인스턴스 가져오기
        val rv_function: RecyclerView = findViewById(R.id.rv_function)
        // SettingEduAdapter 초기화
        settingAdapter = SettingEduAdapter(this)

        // RecyclerView에 수직 및 수평 간격 조정 ItemDecorator 적용
        rv_function.addItemDecoration(VerticalItemDecorator(20))
        rv_function.addItemDecoration(HorizontalItemDecorator(10))

        // RecyclerView에 Adapter 및 LayoutManager 설정
        rv_function.adapter = settingAdapter
        rv_function.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트에 SettingData 객체들 추가
        datas.apply {
            add(SettingData(img = R.drawable.wifi, name = "연결", explain = "Wi-Fi, 블루투스, 비행기 탑승 모드, 데이터 사용"))
            add(SettingData(img = R.drawable.soundimg, name = "소리 및 진동", explain = "소리 모드, 벨소리, 음량"))
            add(SettingData(img = R.drawable.message, name = "알림", explain = "앱 알림, 상태표시줄, 방해 금지"))
            add(SettingData(img = R.drawable.display, name = "디스플레이", explain = "밝기, 블루라이트 필터, 홈 화면"))
            add(SettingData(img = R.drawable.backgroundimg, name = "배경화면", explain = "홈 배경화면, 잠금화면 배경"))
            add(SettingData(img = R.drawable.theme, name = "테마", explain = "테마, 배경화면, 아이콘"))
            add(SettingData(img = R.drawable.lock, name = "잠금화면", explain = "화면 잠금 방식, Always On Display, 시계 스타일"))

            // Adapter에 데이터 설정 및 갱신
            settingAdapter.datas = datas
            settingAdapter.notifyDataSetChanged()
        }
    }
}
