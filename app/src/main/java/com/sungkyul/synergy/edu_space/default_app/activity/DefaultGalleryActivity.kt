package com.sungkyul.synergy.edu_space.default_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryBinding
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryAlbumFragment

class DefaultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryBinding
    private lateinit var albumFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumFragment = DefaultGalleryAlbumFragment(binding.eduScreen)

        replaceFragment(albumFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }
}
