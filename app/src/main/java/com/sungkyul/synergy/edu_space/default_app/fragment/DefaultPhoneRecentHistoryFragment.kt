package com.sungkyul.synergy.edu_space.default_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultPhoneRecentHistoryBinding
import com.sungkyul.synergy.edu_space.default_app.adapter.RecentHistoryAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.RecentHistoryData

class DefaultPhoneRecentHistoryFragment : Fragment() {
    private lateinit var binding: FragmentDefaultPhoneRecentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultPhoneRecentHistoryBinding.inflate(inflater, container, false)

        val recentHistoryArray = ArrayList<RecentHistoryData>()
        recentHistoryArray.add(RecentHistoryData(R.drawable.ic_call_profile_24, R.drawable.ic_incoming_call_24, R.drawable.ic_mic_24, "대장님", "휴대전화", "오후 4:30", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.ic_call_profile_24, R.drawable.ic_callmissing_32, R.drawable.white_circle, "UX/UI 디자이너", "휴대전화", "오후 4:28", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.ic_call_profile_24, R.drawable.ic_call_video, R.drawable.white_circle, "기획자", "휴대전화", "오후 4:25", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.ic_call_profile_24, R.drawable.ic_incoming_call_24, R.drawable.ic_mic_24, ":fearful:", "휴대전화", "오후 4:20", "휴대전화: 010-9876-5432"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.ic_call_profile_24, R.drawable.ic_incoming_call_24, R.drawable.ic_mic_24, "010-1234-5678", "저장 안 됨", "오후 4:15", "휴대전화: 010-1234-5678"))

        val recentHistoryList = binding.recentHistoryList
        recentHistoryList.layoutManager = LinearLayoutManager(binding.root.context)
        recentHistoryList.adapter = RecentHistoryAdapter(recentHistoryArray)

        return binding.root
    }
}
