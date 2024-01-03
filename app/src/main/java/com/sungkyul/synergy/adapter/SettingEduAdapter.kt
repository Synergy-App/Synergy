package com.sungkyul.synergy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.settingedu.SettingDetailActivity
import com.sungkyul.synergy.data.SettingData

/** 교육공간 속 환경설정교육 리사이클러뷰 어댑터 */
class SettingEduAdapter(private val context: Context) : RecyclerView.Adapter<SettingEduAdapter.ViewHolder>() { // 뷰 객체를 그려주는 ViewHolder와 Data와 View를 연결해주는 Adapte

    var datas = mutableListOf<SettingData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.setting_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.tv_rv_name)
        private val txtExplain: TextView = itemView.findViewById(R.id.tv_rv_explain)
        private val imgSetting: ImageView = itemView.findViewById(R.id.img_rv_photo)

        fun bind(item: SettingData) {
            txtName.text = item.name
            txtExplain.text = item.explain
            Glide.with(itemView).load(item.img).into(imgSetting)

            itemView.setOnClickListener {
                val intent = Intent(context, com.sungkyul.synergy.settingedu.SettingDetailActivity::class.java)
                intent.putExtra("data", item)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }}