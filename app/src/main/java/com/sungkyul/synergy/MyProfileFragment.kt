package com.sungkyul.synergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.adapter.MyProfileAdapter
import com.sungkyul.synergy.data.MyProfileItem

class MyProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        val myProfileItems = listOf(
            MyProfileItem("시험결과\n확인하기", R.drawable.illustration),
            MyProfileItem("자주틀린문제\n다시풀기", R.drawable.illustration),
            MyProfileItem("일별학습\n다시풀기", R.drawable.illustration),
            MyProfileItem("오래걸린문제\n다시풀기", R.drawable.illustration),


            )

        val mySpaceRecyclerView: RecyclerView = view.findViewById(R.id.my_space_recyclerView)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mySpaceRecyclerView.layoutManager = layoutManager

        val mySpaceAdapter = MyProfileAdapter(requireContext(), myProfileItems)
        mySpaceRecyclerView.adapter = mySpaceAdapter

        return view
    }
}
