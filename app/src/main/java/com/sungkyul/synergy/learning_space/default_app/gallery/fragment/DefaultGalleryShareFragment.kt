package com.sungkyul.synergy.learning_space.default_app.gallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentDefaultGalleryShareBinding
import com.sungkyul.synergy.utils.edu.EduListener

class DefaultGalleryShareFragment(private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentDefaultGalleryShareBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultGalleryShareBinding.inflate(inflater, container, false)

        return binding.root
    }
}
