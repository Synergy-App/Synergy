package com.sungkyul.synergy.learning_space.default_app.gallery.adapter

import android.content.Context
import android.util.Log
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

class GalleryPictureAdapter(
    private val context: Context,
    private val dataSet: List<GalleryPictureData>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<GalleryPictureAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery_picture, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val galleryPictureData = dataSet[position]
        viewHolder.date.text = galleryPictureData.date

        // GridLayoutManager와 어댑터 설정
        viewHolder.recyclerview.layoutManager = GridLayoutManager(context, 4)
        viewHolder.recyclerview.adapter = GroupPhotosByDateAdapter(galleryPictureData.images)

        // 클릭 리스너 설정
        viewHolder.itemView.setOnClickListener {
            Log.d("GalleryPictureAdapter", "Item clicked at position $position")
            itemClickListener(position)
        }
    }


    override fun getItemCount() = dataSet.size
}
