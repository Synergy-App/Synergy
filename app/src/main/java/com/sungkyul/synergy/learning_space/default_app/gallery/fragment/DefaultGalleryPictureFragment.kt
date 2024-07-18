package com.sungkyul.synergy.learning_space.default_app.gallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultGalleryPictureBinding
import com.sungkyul.synergy.learning_space.default_app.gallery.adapter.GalleryPictureAdapter
import com.sungkyul.synergy.learning_space.default_app.gallery.adapter.GalleryPictureData
import com.sungkyul.synergy.utils.edu.EduListener

class DefaultGalleryPictureFragment(private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentDefaultGalleryPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultGalleryPictureBinding.inflate(inflater, container, false)

        // recyclerview에 들어갈 데이터 셋을 만든다.
        val dataSet = ArrayList<GalleryPictureData>()
        dataSet.add(
            GalleryPictureData(
                "5월 2일",
                arrayListOf(
                    R.drawable.sample_screenshot1,
                    R.drawable.sample_screenshot2
                )
            )
        )
        dataSet.add(
            GalleryPictureData(
                "4월 27일",
                arrayListOf(
                    R.drawable.sample_screenshot1,
                    R.drawable.sample_screenshot2,
                    R.drawable.sample_screenshot1
                )
            )
        )
        for(i in 1..5) {
            dataSet.add(
                GalleryPictureData(
                    "4월 ${25-i}일",
                    arrayListOf(
                        R.drawable.sample_screenshot1,
                        R.drawable.sample_screenshot2,
                        R.drawable.sample_screenshot2,
                        R.drawable.sample_screenshot1,
                        R.drawable.sample_screenshot2,
                        R.drawable.sample_screenshot2
                    )
                )
            )
        }

        val recyclerview = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerview.adapter = GalleryPictureAdapter(requireContext(), dataSet)

        return binding.root
    }
}