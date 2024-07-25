// GalleryPhotoAdapter.kt
package com.sungkyul.synergy.training_space.default_app.camera

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

class GalleryPhotoAdapter(
    private val context: Context,
    private val photoList: List<Int>
) : RecyclerView.Adapter<GalleryPhotoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(photoList[position])
    }

    override fun getItemCount() = photoList.size
}
