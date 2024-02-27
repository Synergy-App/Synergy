package com.sungkyul.synergy.adapter
import com.sungkyul.synergy.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.data.MyProfileItem

class MyProfileAdapter(private val context: Context, private val items: List<MyProfileItem>) : RecyclerView.Adapter<MyProfileAdapter.MySpaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySpaceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_my_profile, parent, false)
        return MySpaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MySpaceViewHolder, position: Int) {
        val currentItem = items[position]
        holder.textView.text = currentItem.title
        Glide.with(context).load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MySpaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.my_profile_item_tv)
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
    }
}
