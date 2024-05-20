package com.sungkyul.synergy.edu_space.default_app.phone.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultPhoneContactBinding
import com.sungkyul.synergy.edu_space.default_app.phone.activity.DefaultPhoneAddActivity
import com.sungkyul.synergy.edu_space.default_app.phone.adapter.ContactAdapter
import com.sungkyul.synergy.edu_space.default_app.phone.adapter.ContactData
import com.sungkyul.synergy.edu_space.icon_edu.adapter.IconEduAdapter
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.utils.GalaxyButton
import com.sungkyul.synergy.utils.edu.EduListener
import com.sungkyul.synergy.utils.edu.EduScreen

class DefaultPhoneContactFragment(private val addedContact: ContactData? = null, private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentDefaultPhoneContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private val contactArray = ArrayList<ContactData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultPhoneContactBinding.inflate(inflater, container, false)

        // 기존 연락처 목록 추가
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "대장님", "휴대전화: 010-1234-1234"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너","휴대전화: 010-1234-1234"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "기획자","휴대전화: 010-1234-1234"))


        // 새 연락처 추가
        if(addedContact != null) {
            contactArray.add(addedContact)
        }

        // 어댑터 설정
        contactAdapter = ContactAdapter(contactArray, eduListener)
        binding.contactList.layoutManager = LinearLayoutManager(requireContext())
        binding.contactList.adapter = contactAdapter

        // add_button 클릭 이벤트 처리
        binding.addButton.setOnClickListener {
            val intent = Intent(requireContext(), DefaultPhoneAddActivity::class.java)
            startActivity(intent)

        }

        binding.searchButton.setOnTouchListener { _, _ ->
            // searchEditText에 포커스를 줌으로써 키보드가 나타나고 활성화됩니다.
            binding.searchEditText.requestFocus()

            // true를 반환하여 이벤트가 소비되었음을 알립니다.
            true
        }

        return binding.root
    }




}
