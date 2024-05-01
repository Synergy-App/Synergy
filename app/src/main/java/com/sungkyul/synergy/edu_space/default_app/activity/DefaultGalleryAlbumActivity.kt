package com.sungkyul.synergy.edu_space.default_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryAlbumBinding
import com.sungkyul.synergy.edu_space.default_app.adapter.GalleryAlbumAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.GalleryAlbumData

class DefaultGalleryAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultGalleryAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = ArrayList<GalleryAlbumData>()
        for(i in 1..10) {
            dataSet.add(
                GalleryAlbumData(
                    R.drawable.sample_screenshot1,
                    "시너지",
                    123
                )
            )
        }

        val recyclerview = binding.recyclerview
        recyclerview.layoutManager = GridLayoutManager(binding.root.context, 3)
        recyclerview.adapter = GalleryAlbumAdapter(dataSet)
    }
}
