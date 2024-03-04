package com.sungkyul.synergy.edu_space.settingedu.adapter

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
import com.sungkyul.synergy.edu_space.settingedu.activity.SettingDetailActivity
import com.sungkyul.synergy.edu_space.settingedu.data.SettingData
import com.sungkyul.synergy.edu_space.settingedu.activity.SettingDisplayActivity
import com.sungkyul.synergy.utils.EduListener

/** 교육공간 속 환경설정교육 리사이클러뷰 어댑터 */
class SettingEduAdapter(private val context: Context, private val eduListener: EduListener) : RecyclerView.Adapter<SettingEduAdapter.ViewHolder>() { // 뷰 객체를 그려주는 ViewHolder와 Data와 View를 연결해주는 Adapte

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
                if (item.name == "디스플레이") {
                    if(eduListener.onAction("click_display_item")) {
                        // 디스플레이를 클릭한 경우 SettingDisplayActivity 시작
                        //val intent = Intent(context, SettingDisplayActivity::class.java)
                        val intent = Intent(context, SettingDisplayActivity::class.java)
                        context.startActivity(intent)
                    }
                } else {
                    if(eduListener.onAction("click_other_item")) {
                        // 다른 경우 SettingDetailActivity 시작
                        val intent = Intent(context, SettingDetailActivity::class.java)
                        intent.putExtra("data", item)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}
