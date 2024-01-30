package com.sungkyul.synergy.edu_space.default_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPhoneBinding
import com.sungkyul.synergy.databinding.FragmentContactBinding
import com.sungkyul.synergy.databinding.FragmentKeypadBinding
import com.sungkyul.synergy.databinding.FragmentRecentHistoryBinding
import com.sungkyul.synergy.util.Animation

private lateinit var binding: FragmentRecentHistoryBinding

class RecentHistoryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecentHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_recent_history, container, false)
    }
}
