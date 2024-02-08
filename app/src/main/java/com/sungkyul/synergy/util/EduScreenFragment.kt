package com.sungkyul.synergy.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentEduScreenBinding
import com.sungkyul.synergy.databinding.FragmentDefaultCallKeypadBinding

// TODO: 어떻게 해야 교육 안내 메시지를 적은 반복 작업으로 보여줄 수 있을까?
class EduScreenFragment : Fragment() {
    private lateinit var binding: FragmentEduScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEduScreenBinding.inflate(inflater, container, false)

        return binding.root
    }
}
