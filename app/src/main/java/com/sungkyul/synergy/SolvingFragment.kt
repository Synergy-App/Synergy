package com.sungkyul.synergy

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.adapter.EduButtonItem
import com.sungkyul.synergy.learning_space.adapter.LearningButtonAdapter

class SolvingFragment : Fragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_solving, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val buttonItemList = listOf(
            EduButtonItem("아이콘", R.drawable.ic_edu_note),
            EduButtonItem("화면구성", R.drawable.ic_edu_gall),
            EduButtonItem("기본앱", R.drawable.ic_edu_app2),
            EduButtonItem("환경 설정", R.drawable.ic_edubutton_setting),
            EduButtonItem("계정 생성", R.drawable.ic_edu_create),
            EduButtonItem("앱 설치", R.drawable.ic_edubutton_download),
            EduButtonItem("카카오톡", R.drawable.ic_edubutton_kakaotalk),
            EduButtonItem("네이버", R.drawable.ic_edubutton_naver)
//          EduButtonItem("코레일", R.drawable.ic_edubutton_korail),
//          EduButtonItem("카카오택시", R.drawable.ic_edubutton_kakaotaxi),
//          EduButtonItem("배달의 민족", R.drawable.ic_edubutton_delivery)
        )
        val adapter = LearningButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter

        setDynamicTextSize(view) // 추가: 텍스트 크기 설정

        return view
    }

    private fun getScreenSize(): Point {
        val display = (requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize(): Pair<Int, Int> {
        val screenSize = getScreenSize()
        val density = resources.displayMetrics.density

        val standardSizeX = (screenSize.x / density).toInt()
        val standardSizeY = (screenSize.y / density).toInt()

        return Pair(standardSizeX, standardSizeY)
    }

    private fun setDynamicTextSize(view: View) {
        val (standardSizeX, standardSizeY) = getStandardSize()

        // 각각의 텍스트 요소에 다른 크기 설정
        val learingTitle: TextView = view.findViewById(R.id.learingTitle)
        learingTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 12).toFloat())

        val learingSubtitle: TextView = view.findViewById(R.id.learingSubtitle)
        learingSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        // headerImage의 높이 설정
        val headerImage: View = view.findViewById(R.id.headerImage)
        val headerImageHeight = (standardSizeY * 0.5).toInt() // 높이를 화면 높이의 10%로 설정
        headerImage.layoutParams.height = headerImageHeight
        headerImage.requestLayout()
    }
}
