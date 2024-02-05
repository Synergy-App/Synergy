package com.sungkyul.synergy.edu_space.default_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentContactBinding

private lateinit var binding: FragmentContactBinding

class ContactFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentContactBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
}
