package com.sungkyul.synergy

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.adapter.EduButtonAdapter
import com.sungkyul.synergy.adapter.EduButtonItem

class LearningFragment : Fragment() {

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val buttonItemList = listOf(
            EduButtonItem("기초", R.drawable.ic_edu_note),
            EduButtonItem("화면구성", R.drawable.ic_edu_gall),
            EduButtonItem("기본 앱", R.drawable.ic_edu_app2),
            EduButtonItem("환경 설정", R.drawable.ic_edubutton_setting),
            EduButtonItem("계정 생성", R.drawable.ic_edu_create),
            EduButtonItem("앱 설치", R.drawable.ic_edubutton_download),
            EduButtonItem("카카오톡", R.drawable.ic_edubutton_kakaotalk),
            EduButtonItem("네이버", R.drawable.ic_edubutton_naver)
        )

        val adapter = EduButtonAdapter(requireContext(), childFragmentManager, buttonItemList)
        recyclerView.adapter = adapter

        recyclerView.setOnTouchListener { _, _ -> false }
        recyclerView.setOnScrollChangeListener { _, _, _, _, _ -> }

        return view
    }

    fun handleOnBackPressed(): Boolean {
        if (backPressedOnce) {
            activity?.finish()
            return true
        }

        this.backPressedOnce = true
        Toast.makeText(requireContext(), "뒤로가기를 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressHandler.postDelayed(backPressRunnable, 2000)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressHandler.removeCallbacks(backPressRunnable)
    }
}
