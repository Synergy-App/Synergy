package com.sungkyul.synergy.edu_space.default_app.adapter

import com.sungkyul.synergy.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class GalleryStoryData(
    val image: Int,
    val date: String
)

class GalleryStoryAdapter(private val dataSet: ArrayList<GalleryStoryData>): RecyclerView.Adapter<GalleryStoryAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.story_image)
        val date: TextView = view.findViewById(R.id.story_date)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_gallery_story, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.image.setImageResource(dataSet[position].image)
        viewHolder.date.text = dataSet[position].date
    }

    override fun getItemCount() = dataSet.size
}
