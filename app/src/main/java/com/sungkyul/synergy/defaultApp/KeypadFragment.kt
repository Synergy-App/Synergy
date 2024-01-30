package com.sungkyul.synergy.defaultApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.sungkyul.synergy.databinding.FragmentKeypadBinding
import com.sungkyul.synergy.util.Animation

class KeypadFragment : Fragment() {
    private lateinit var binding: FragmentKeypadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeypadBinding.inflate(inflater, container, false)

        // 버튼 리스트
        val keyButtons = listOf<View>(
            binding.contactAddition,
            binding.key1,
            binding.key2,
            binding.key3,
            binding.key4,
            binding.key5,
            binding.key6,
            binding.key7,
            binding.key8,
            binding.key9,
            binding.keyStar,
            binding.key0,
            binding.keySharp,
            binding.videoCall,
            binding.call,
            binding.back
        )

        for(i in keyButtons) {
            // 각 버튼마다 터치 애니메이션을 추가한다.
            Animation.setViewBgAlphaAnimationOnTouch(
                i,
                0,
                0,
                36,
                250
            )


        }

        return binding.root
    }
}
