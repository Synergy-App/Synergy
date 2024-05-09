package com.sungkyul.synergy.edu_space.default_app.message.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

//메세지 연락처 데이터
data class MessageContactData(
    val Mprofile: Int,
    val name: String,
    val phoneNum: String
)
class MessageSelectAdapter (private val dataSet: ArrayList<MessageContactData>): RecyclerView.Adapter<MessageSelectAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Mprofile: ImageView//프로필 표시하는 부분
        val name: TextView//이름
        val phoneNum: TextView

        init {
            Mprofile = view.findViewById(R.id.Mprofile)
            name = view.findViewById(R.id.name)
            phoneNum = view.findViewById(R.id.phoneNum)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_message_select, viewGroup, false)

        return MessageSelectAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MessageSelectAdapter.ViewHolder, position: Int) {
        viewHolder.Mprofile.setImageResource(dataSet[position].Mprofile)
        viewHolder.name.text = dataSet[position].name
        viewHolder.phoneNum.text = dataSet[position].phoneNum
    }

    override fun getItemCount() = dataSet.size


}