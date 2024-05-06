package com.sungkyul.synergy.edu_space.default_app.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class GalleryPictureData(
    val date: String,
    val images: List<Int>
)

class GalleryPictureAdapter(private val context: Context, private val dataSet: ArrayList<GalleryPictureData>): RecyclerView.Adapter<GalleryPictureAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_gallery_picture, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.date.text = dataSet[position].date
        viewHolder.recyclerview.layoutManager = GridLayoutManager(context, 4)
        viewHolder.recyclerview.adapter = GroupPhotosByDateAdapter(dataSet[position].images)
    }

    override fun getItemCount() = dataSet.size
}
