package com.sungkyul.synergy.training.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training.adapter.TrainingButtonAdapter
import com.sungkyul.synergy.training.data.TrainingButtomItem

class TrainingFragment : Fragment() { // Fragment를 상속받아야 합니다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.activity_training, container, false) // fragment_training.xml 파일을 inflate합니다.

        val recyclerView: RecyclerView = view.findViewById(R.id.training_recyclerView)
        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val TrainingbuttonItemList = listOf(
            TrainingButtomItem("아이콘", R.drawable.ic_edubotton_icon),
            TrainingButtomItem("화면구성", R.drawable.ic_edubutton_screen),
            TrainingButtomItem("기본앱", R.drawable.ic_edubutton_default),
            TrainingButtomItem("환경 설정", R.drawable.ic_edubutton_setting),
            TrainingButtomItem("계정 생성", R.drawable.ic_edubutton_account),
            TrainingButtomItem("앱 설치", R.drawable.ic_edubutton_download),
            TrainingButtomItem("카카오톡", R.drawable.ic_edubutton_kakaotalk),
            TrainingButtomItem("네이버", R.drawable.ic_edubutton_naver),
            TrainingButtomItem("코레일", R.drawable.ic_edubutton_korail),
            TrainingButtomItem("카카오택시", R.drawable.ic_edubutton_kakaotaxi),
            TrainingButtomItem("배달의 민족", R.drawable.ic_edubutton_delivery)
        )
        val adapter = TrainingButtonAdapter(TrainingbuttonItemList)
        recyclerView.adapter = adapter

        return view
    }
}
