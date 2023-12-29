package com.sungkyul.synergy.kakaotalk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R

class ProfileAdapter(private val context: Context) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var datas = mutableListOf<profileItem>() // 데이터 리스트 초기화
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.kakao_friends_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val profile_name: TextView = itemView.findViewById(R.id.profile_name)
        private val profile_message: TextView = itemView.findViewById(R.id.profile_message)
        private val profile_iv: ImageView = itemView.findViewById(R.id.profile_iv)

        fun bind(item: profileItem) {
            profile_name.text = item.name
            profile_message.text = item.message
            profile_iv.setImageResource(item.image)

        }
    }
}
