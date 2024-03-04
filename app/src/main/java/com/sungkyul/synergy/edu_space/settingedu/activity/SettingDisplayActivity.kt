package com.sungkyul.synergy.edu_space.settingedu.activity

import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivitySettingDisplayBinding
import com.sungkyul.synergy.edu_space.settingedu.adapter.SettingDisplayAdapter
import com.sungkyul.synergy.edu_space.settingedu.data.DisplayData
import com.sungkyul.synergy.utils.EduCourses

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
    private lateinit var binding: ActivitySettingDisplayBinding

    // SettingDisplayAdapter 및 데이터 리스트를 초기화할 변수 선언
    lateinit var displayAdapter: SettingDisplayAdapter
    val ddatas = mutableListOf<DisplayData>()

    // 액티비티가 생성될 때 호출되는 onCreate 메서드 재정의
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingDisplayBinding.inflate(layoutInflater)
        // 레이아웃 설정
        setContentView(binding.root)
        // RecyclerView 초기화 함수 호출
        initRecycler()

        // SeekBar 초기화 및 리스너 등록
        val seekBar: SeekBar = findViewById(R.id.sb_Brightness)
        seekBar.progress = 0

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // SeekBar의 값이 변경될 때 호출되는 콜백
                // 여기에서 밝기를 조절하도록 설정

                // 밝기 값이 0.0부터 1.0까지의 범위로 정규화되어야 합니다.
                val normalizedBrightness = progress / 100.0f

                // 앱의 Window 속성 가져오기
                val window = window
                val layoutParams = window.attributes

                // 밝기 설정
                layoutParams.screenBrightness = normalizedBrightness

                // 앱의 화면 속성 업데이트
                window.attributes = layoutParams

                // 예제로 밝기 값에 따라 텍스트뷰에 표시하는 코드
                val brightnessTextView: TextView = findViewById(R.id.tv_Brightness)
                brightnessTextView.text = "밝기: $progress"

                binding.eduScreen.onAction("change_light_bar", progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 사용자가 SeekBar 터치를 시작할 때 호출되는 콜백
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 사용자가 SeekBar 터치를 끝낼 때 호출되는 콜백
            }
        })

        // 교육을 진행해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = EduCourses.settingsDisplayCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳
            }
            binding.eduScreen.start(this)
        }
    }



    // RecyclerView를 초기화하고 설정하는 함수
    private fun initRecycler() {
        // RecyclerView 인스턴스 가져오기
        val rv_display: RecyclerView = findViewById(R.id.rv_display)
        // SettingEduAdapter 초기화
        displayAdapter = SettingDisplayAdapter(this, binding.eduScreen)

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

        // TODO(나중에 한 번 더 스크롤 이벤트 쓰게 되면 수정해야 할 듯)
        rv_display.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                // '탭을 해야지 스크롤을 다시 위로 올리면 어떡해!! 다시 내려!!'를 의미한다.
                if(rv_display.canScrollVertically(1) && binding.eduScreen.getAction().id == "tap_font_item") {
                    binding.eduScreen.prev()
                    Log.i("scroll", "마지막 아님!")
                }

                // 스크롤이 최하단에 도달한 경우
                if(!rv_display.canScrollVertically(1) && binding.eduScreen.getAction().id == "scroll_to_bottom") {
                    binding.eduScreen.next()
                    Log.i("scroll", "마지막!")
                }
            }
        })
    }
}
