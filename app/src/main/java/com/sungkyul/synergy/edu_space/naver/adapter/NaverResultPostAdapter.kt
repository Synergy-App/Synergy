package com.sungkyul.synergy.edu_space.naver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class NaverResultPostData(
    val linkImage: Int,
    val linkName: String,
    val postTitle: String,
    val postContent: String,
    val postImage: Int
)

class NaverResultPostAdapter(private val dataSet: ArrayList<NaverResultPostData>): RecyclerView.Adapter<NaverResultPostAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linkImage: ImageView = view.findViewById(R.id.link_image)
        val linkName: TextView = view.findViewById(R.id.link_name)
        val postTitle: TextView = view.findViewById(R.id.post_title)
        val postContent: TextView = view.findViewById(R.id.post_content)
        val postImage: ImageView = view.findViewById(R.id.post_image)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_naver_result_post, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.linkImage.setImageResource(dataSet[position].linkImage)
        viewHolder.linkName.text = dataSet[position].linkName
        viewHolder.postTitle.text = dataSet[position].postTitle
        viewHolder.postContent.text = dataSet[position].postContent
        viewHolder.postImage.setImageResource(dataSet[position].postImage)
    }

    override fun getItemCount() = dataSet.size
}
