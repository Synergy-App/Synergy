package com.sungkyul.synergy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.data.DisplayData

class SettingDisplayAdapter(private val context: Context) : RecyclerView.Adapter<SettingDisplayAdapter.ViewHolder>() {

    var datas = mutableListOf<DisplayData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_setting_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
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