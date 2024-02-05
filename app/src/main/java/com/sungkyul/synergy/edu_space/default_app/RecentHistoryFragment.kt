package com.sungkyul.synergy.edu_space.default_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentRecentHistoryBinding

class RecentHistoryFragment : Fragment() {
    private lateinit var binding: FragmentRecentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentHistoryBinding.inflate(inflater, container, false)

        val recentHistoryArray = ArrayList<RecentHistoryData>()
        recentHistoryArray.add(RecentHistoryData(R.drawable.baseline_person_24, R.drawable.lock, R.drawable.white_circle, "대장님", "휴대전화", "오후 4:30", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.baseline_person_24, R.drawable.lock, R.drawable.white_circle, "UX/UI 디자이너", "휴대전화", "오후 4:28", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.baseline_person_24, R.drawable.lock, R.drawable.white_circle, "기획자", "휴대전화", "오후 4:25", "휴대전화: 010-1234-5678"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.baseline_person_24, R.drawable.lock, R.drawable.white_circle, ":fearful:", "휴대전화", "오후 4:20", "휴대전화: 010-9876-5432"))
        recentHistoryArray.add(RecentHistoryData(R.drawable.baseline_person_24, R.drawable.lock, R.drawable.white_circle, "010-1234-5678", "저장 안 됨", "오후 4:15", "휴대전화: 010-1234-5678"))

        val recentHistoryList = binding.recentHistoryList
        recentHistoryList.layoutManager = LinearLayoutManager(binding.root.context)
        recentHistoryList.adapter = RecentHistoryAdapter(recentHistoryArray)

        return binding.root
    }
}
