package com.sungkyul.synergy.edu_space.default_app.message.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMessageSelectBinding
import com.sungkyul.synergy.edu_space.default_app.message.adapter.MessageContactData
import com.sungkyul.synergy.edu_space.default_app.message.adapter.MessageSelectAdapter

class DefaultMessageSelectFragment : Fragment() {
    private lateinit var binding: ActivityDefaultMessageSelectBinding
    private lateinit var contactAdapter: MessageSelectAdapter
    private val contactArray = ArrayList<MessageContactData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityDefaultMessageSelectBinding.inflate(inflater, container, false)

        // 기존 연락처 목록 추가
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "대장님", "휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, "기획자","휴대전화: 010-1234-1234"))
        contactArray.add(MessageContactData(R.drawable.ic_person_black_24dp, ":fearful:","휴대전화: 010-1234-1234"))


        // 어댑터 설정
        contactAdapter = MessageSelectAdapter(contactArray)
        binding.messageContactList.layoutManager = LinearLayoutManager(requireContext())
        binding.messageContactList.adapter = contactAdapter


        return binding.root
    }


}