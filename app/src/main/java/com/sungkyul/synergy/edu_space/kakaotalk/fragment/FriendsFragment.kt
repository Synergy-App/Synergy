package com.sungkyul.synergy.edu_space.kakaotalk.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotalk.adapter.ProfileAdapter
import com.sungkyul.synergy.edu_space.kakaotalk.data.profileItem

/** 카카오톡 친구 리스트뷰 화면 Fragment */
class FriendsFragment : Fragment() {
    // RecyclerView 및 관련 변수 선언
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private var profileItems = mutableListOf<profileItem>() // RecyclerView에 표시할 데이터 리스트

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)


        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.kakao_friends_rv)
        adapter = ProfileAdapter(requireContext()) // ProfileAdapter 초기화
        recyclerView.adapter = adapter

        // LayoutManager 설정 (LinearLayoutManager 사용)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 데이터 설정 (임시 데이터 예시)
        profileItems.add(profileItem(R.drawable.kakao_profile, "서인국", "안녕하세요"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "임영웅", "별빛같은 사랑아 ~ "))
        profileItems.add(profileItem(R.drawable.kakao_profile, "박연우", "너는 내 운명"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "추민영", "키키키"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "노승현", ""))
        profileItems.add(profileItem(R.drawable.kakao_profile, "박지현", ""))
        profileItems.add(profileItem(R.drawable.kakao_profile, "덱스", "멋쟁이"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "심춘희", "그대와 나"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "박박박", "유아쏘뷰티풀"))
        profileItems.add(profileItem(R.drawable.kakao_profile, "황진닝", ""))


        adapter.datas = profileItems // Adapter에 데이터 설정
        return view


    }
}