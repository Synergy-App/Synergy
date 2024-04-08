package com.sungkyul.synergy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val buttonItemList = listOf(
            EduButtonItem("아이콘", R.drawable.ic_edubotton_icon,0.0f,1.0f),
            EduButtonItem("화면구성", R.drawable.ic_edubutton_screen,0.0f,0.0f),
            EduButtonItem("기본앱", R.drawable.ic_edubutton_default,0.0f,0.0f),
            EduButtonItem("환경 설정", R.drawable.ic_edubutton_setting,0.0f,0.0f),
            EduButtonItem("계정 생성", R.drawable.ic_edubutton_account,0.0f,0.0f),
            EduButtonItem("앱 설치", R.drawable.ic_edubutton_download,0.0f,0.0f),
            EduButtonItem("카카오톡", R.drawable.ic_edubutton_kakaotalk,0.0f,0.0f),
            EduButtonItem("네이버", R.drawable.ic_edubutton_naver,0.0f,0.0f),
            EduButtonItem("코레일", R.drawable.ic_edubutton_korail,0.0f,0.0f),
            EduButtonItem("카카오택시", R.drawable.ic_edubutton_kakaotaxi,0.0f,0.0f),
            EduButtonItem("배달의 민족", R.drawable.ic_edubutton_delivery,0.0f,0.0f)
        )
        val adapter = LearningButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter

        return view
    }
}
