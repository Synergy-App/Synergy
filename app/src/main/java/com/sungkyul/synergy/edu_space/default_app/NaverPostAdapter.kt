package com.sungkyul.synergy.edu_space.default_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class NaverPostData(
    val profileImage: Int,
    val profileName: String,
    val date: String,
    val postTitle: String,
    val postContent: String,
    val postImage: Int
)

class NaverPostAdapter(private val dataSet: ArrayList<NaverPostData>): RecyclerView.Adapter<NaverPostAdapter.ViewHolder>() {
    var height = 0 // 네이버 포스트 리스트의 높이

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val naverPost: LinearLayout = view.findViewById(R.id.naver_post)
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val profileName: TextView = view.findViewById(R.id.profile_name)
        val date: TextView = view.findViewById(R.id.date)
        val postTitle: TextView = view.findViewById(R.id.post_title)
        val postContent: TextView = view.findViewById(R.id.post_content)
        val postImage: ImageView = view.findViewById(R.id.post_image)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_naver_post, viewGroup, false)

        // 각 아이템의 높이를 측정해서 전체 높이에 추가한다.
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        height += view.measuredHeight

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.profileImage.setImageResource(dataSet[position].profileImage)
        viewHolder.profileName.text = dataSet[position].profileName
        viewHolder.date.text = dataSet[position].date
        viewHolder.postTitle.text = dataSet[position].postTitle
        viewHolder.postContent.text = dataSet[position].postContent
        viewHolder.postImage.setImageResource(dataSet[position].postImage)
    }

    override fun getItemCount() = dataSet.size
}
