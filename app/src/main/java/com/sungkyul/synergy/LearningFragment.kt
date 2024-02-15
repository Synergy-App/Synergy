package com.sungkyul.synergy

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sungkyul.synergy.adapter.EduButtonAdapter
import com.sungkyul.synergy.data.EduButtonItem

/** 학습하기 (교육공간) fragment
 * MPAndroidChart라이브러리 나중에 분리해야할듯 */

class LearningFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning, container, false)


        val barChart: HorizontalBarChart = view.findViewById(R.id.barChart)

        val myStudyTime = 65f // 예시: 1시간 5분을 분 단위로 변환하여 설정
        val totalStudyTime = 180f // 전체 값 설정(우선 3시간이 최대라고 설정해둠)

        // 전체 막대 설정
        val totalEntries = ArrayList<BarEntry>()
        totalEntries.add(BarEntry(0f, totalStudyTime)) // 전체 막대

        val totalBarDataSet = BarDataSet(totalEntries, "Total Study Time")
        totalBarDataSet.color = requireContext().getColor(R.color.chatColor) // 투명한 색으로 설정하여 숨김

        // 공부한 시간 막대 설정
        val studyEntries = ArrayList<BarEntry>()
        studyEntries.add(BarEntry(0f, myStudyTime)) // 공부한 막대

        val studyBarDataSet = BarDataSet(studyEntries, "My Study Time")
        studyBarDataSet.color = requireContext().getColor(R.color.learnChatColor) // 공부한 막대 색상 설정

        // 데이터 설정
        val data = BarData(totalBarDataSet, studyBarDataSet)
        data.barWidth = 0.1f

        // X축 설정
        val xAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawLabels(false)

        // Y축 설정
        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawLabels(false) // 왼쪽 Y축 텍스트 숨기기
        leftAxis.axisMinimum = 0f

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawAxisLine(false)
        rightAxis.setDrawLabels(false)

        // 차트 설정
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.setTouchEnabled(false)
        barChart.animateY(1000)
        barChart.data = data

        // RecyclerView 초기화 및 설정
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val buttonItemList = listOf(
            EduButtonItem("아이콘", Color.parseColor("#ffffff")),
            EduButtonItem("화면구성", Color.parseColor("#ffffff")),
            EduButtonItem("기본 앱", Color.parseColor("#ffffff")),
            EduButtonItem("환경설정", Color.parseColor("#ffffff")),
            EduButtonItem("계정 생성", Color.parseColor("#ffffff")),
            EduButtonItem("앱 설치", Color.parseColor("#ffffff")),
            EduButtonItem("카카오톡", Color.parseColor("#ffffff")),
            EduButtonItem("네이버", Color.parseColor("#ffffff")),
            EduButtonItem("코레일", Color.parseColor("#ffffff")),
            EduButtonItem("카카오택시", Color.parseColor("#ffffff")),
            EduButtonItem("배달의 민족", Color.parseColor("#ffffff")),
        )
        val adapter = EduButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter

        return view
    }
}
