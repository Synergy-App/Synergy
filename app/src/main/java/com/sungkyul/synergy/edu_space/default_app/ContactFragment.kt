package com.sungkyul.synergy.edu_space.default_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        val contactArray = ArrayList<ContactData>()
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "대장님"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "기획자"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, ":fearful:"))

        val contactList = binding.contactList
        contactList.layoutManager = LinearLayoutManager(binding.root.context)
        contactList.adapter = ContactAdapter(contactArray)
        
        return binding.root
    }
}
