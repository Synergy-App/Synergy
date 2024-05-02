package com.sungkyul.synergy.edu_space.default_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultGalleryAlbumBinding
import com.sungkyul.synergy.databinding.FragmentDefaultGalleryStoryBinding
import com.sungkyul.synergy.edu_space.default_app.adapter.GalleryAlbumAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.GalleryAlbumData
import com.sungkyul.synergy.utils.edu.EduListener

class DefaultGalleryStoryFragment(private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentDefaultGalleryStoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultGalleryStoryBinding.inflate(inflater, container, false)

        return binding.root
    }
}
