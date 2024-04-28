package com.sungkyul.synergy.edu_space.default_app.adapter

import com.sungkyul.synergy.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class AlbumData(
    val image: Int,
    val name: String,
    val num: Int
)

class AlbumAdapter(private val dataSet: ArrayList<AlbumData>): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.album_image)
        val name: TextView = view.findViewById(R.id.album_name)
        val num: TextView = view.findViewById(R.id.album_num)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_album_card, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.image.setImageResource(dataSet[position].image)
        viewHolder.name.text = dataSet[position].name
        viewHolder.num.text = dataSet[position].num.toString()
    }

    override fun getItemCount() = dataSet.size
}
