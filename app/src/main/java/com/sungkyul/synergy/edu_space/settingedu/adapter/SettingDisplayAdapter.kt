package com.sungkyul.synergy.edu_space.settingedu.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.settingedu.data.DisplayData
import com.sungkyul.synergy.edu_space.settingedu.activity.SettingFontSizeActivity

class SettingDisplayAdapter(private val context: Context) : RecyclerView.Adapter<SettingDisplayAdapter.ViewHolder>() {

    var datas = mutableListOf<DisplayData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_setting_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {
            // "글자 크기와 스타일" 아이템 클릭 시 SettingFontSizeActivity로 이동
            val intent = Intent(context, SettingFontSizeActivity::class.java)
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtdName: TextView = itemView.findViewById(R.id.tv_rv_dname)
        private val txtdExplain: TextView = itemView.findViewById(R.id.tv_rv_dexplain)

        fun bind(item: DisplayData) {
            txtdName.text = item.dname
            txtdExplain.text = item.dexplain.toString()

        }
    }


}