package com.sungkyul.synergy.training_space.default_app.camera

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultGalleryPictureBinding
import com.sungkyul.synergy.learning_space.default_app.gallery.adapter.GalleryPictureAdapter
import com.sungkyul.synergy.learning_space.default_app.gallery.adapter.GalleryPictureData

class DefaultGalleryPictureExamFragment : Fragment() {

    private lateinit var binding: FragmentDefaultGalleryPictureBinding

    companion object {
        private const val ARG_EDU_SCREEN = "edu_screen"

        // Factory method to create a new instance of the Fragment with arguments
        fun newInstance(eduScreen: String): DefaultGalleryPictureExamFragment {
            val fragment = DefaultGalleryPictureExamFragment()
            val args = Bundle()
            args.putString(ARG_EDU_SCREEN, eduScreen)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultGalleryPictureBinding.inflate(inflater, container, false)

        // Retrieve arguments passed to the fragment
        val eduScreen = arguments?.getString(ARG_EDU_SCREEN)

        // Create dataset
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
                    R.drawable.gallery_apple,
                    R.drawable.sample_screenshot1
                )
            )
        )
        for (i in 1..5) {
            dataSet.add(
                GalleryPictureData(
                    "4월 ${25 - i}일",
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

        // RecyclerView 설정
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = GalleryPictureAdapter(requireContext(), dataSet) { position ->
            val galleryPictureData = dataSet[position]
            if (galleryPictureData.images.contains(R.drawable.gallery_apple)) {
                moveToScreen()
            }
        }

        return binding.root
    }

    private fun moveToScreen() {
        val intent = Intent(requireContext(), PracticeResultGalleryActivity::class.java)
        startActivity(intent)
    }
}
