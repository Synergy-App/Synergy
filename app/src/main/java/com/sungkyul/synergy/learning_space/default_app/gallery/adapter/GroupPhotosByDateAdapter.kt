package com.sungkyul.synergy.learning_space.default_app.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

// 날짜별 사진 그룹 어댑터
class GroupPhotosByDateAdapter(private val dataSet: List<Int>): RecyclerView.Adapter<GroupPhotosByDateAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_group_photos_by_date, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.photo.setImageResource(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}