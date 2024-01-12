package com.sungkyul.synergy.settingedu

import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.adapter.SettingDisplayAdapter
import com.sungkyul.synergy.data.DisplayData

/** 교육공간 속 환경설정교육 속 디스플레이 액티비티 */

// RecyclerView의 Item 간격 조정을 위한 ItemDecoration 클래스 정의
class dVerticalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets 메서드를 재정의하여 Item 간격 설정
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 상하 간격 설정
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}

// RecyclerView의 Item 간격 조정을 위한 ItemDecoration 클래스 정의
class dHorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets 메서드를 재정의하여 Item 간격 설정
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 좌우 간격 설정
        outRect.left = divHeight
        outRect.right = divHeight
    }
}

// AppCompatActivity를 상속받는 SettingDisplayActivity 클래스 정의
class SettingDisplayActivity : AppCompatActivity() {

    // SettingDisplayAdapter 및 데이터 리스트를 초기화할 변수 선언
    lateinit var displayAdapter: SettingDisplayAdapter
    val ddatas = mutableListOf<DisplayData>()

    // 액티비티가 생성될 때 호출되는 onCreate 메서드 재정의
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃 설정
        setContentView(R.layout.activity_setting_display)
        // RecyclerView 초기화 함수 호출
        initRecycler()
    }

    // RecyclerView를 초기화하고 설정하는 함수
    private fun initRecycler() {
        // RecyclerView 인스턴스 가져오기
        val rv_display: RecyclerView = findViewById(R.id.rv_display)
        // SettingEduAdapter 초기화
        displayAdapter = SettingDisplayAdapter(this)

        // RecyclerView에 수직 및 수평 간격 조정 ItemDecorator 적용
        rv_display.addItemDecoration(VerticalItemDecorator(20))
        rv_display.addItemDecoration(HorizontalItemDecorator(10))

        // RecyclerView에 Adapter 및 LayoutManager 설정
        rv_display.adapter = displayAdapter
        rv_display.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트에 DisplayData 객체들 추가
        ddatas.apply {
            add(DisplayData(dname = "밝기 최적화", dexplain = "사용 중 (메인 화면)"))
            add(DisplayData(dname = "부드러운 모션 및 화면 전환", dexplain = "최적화"))
            add(DisplayData(dname = "편안하게 화면 보기", dexplain = ""))
            add(DisplayData(dname = "화면 모드", dexplain = "선명한 화면"))
            add(DisplayData(dname = "글자 크기와 스타일", dexplain = ""))
            add(DisplayData(dname = "화면 크게/작게", dexplain = ""))
            add(DisplayData(dname = "전체 화면 비율로 사용할 앱", dexplain = ""))
            add(DisplayData(dname = "카메라 영역", dexplain = ""))
            add(DisplayData(dname = "화면 자동 꺼짐 시간", dexplain = "2분"))



            // Adapter에 데이터 설정 및 갱신
            displayAdapter.datas = ddatas
            displayAdapter.notifyDataSetChanged()
        }
    }
}
