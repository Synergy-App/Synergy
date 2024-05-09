package com.sungkyul.synergy.edu_space.default_app.message.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AnimUtils

const val VIEW_TYPE_YOU = 0
const val VIEW_TYPE_ME = 1

interface MessageData

data class YourMessageData(
    val profileImage: Int,
    val message: String,
    val date: String,
    val time: String
): MessageData

data class MyMessageData(
    val message: String,
    val date: String,
    val time: String
): MessageData

class MessageAdapter(private val dataSet: ArrayList<MessageData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemLayout: LinearLayout
        val hiddenLayout : LinearLayout
        var maxHeight: Int = 0 // 히든 레이아웃의 최대 높이
        var toggle: Boolean = false // 히든 레이아웃 토글 (false이면 접기, true이면 펼치기)
        init {
            itemLayout = view.findViewById(R.id.item_layout)
            hiddenLayout = view.findViewById(R.id.contact_hidden_layout)


            // 히든 레이아웃의 초기 크기를 먼저 측정한다.
            hiddenLayout.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            maxHeight = hiddenLayout.measuredHeight

            hiddenLayout.layoutParams.height = 0

            // 아이템 선택 이벤트
            itemLayout.setOnClickListener {
                // 히든 레이아웃을 펼쳤다 접었다 할 수 있다.
                if(toggle) {
                    AnimUtils.startHeightAnimation(hiddenLayout, 250L, hiddenLayout.height, 0)
                    toggle = false
                } else {
                    AnimUtils.startHeightAnimation(hiddenLayout, 250L, hiddenLayout.height, maxHeight)
                    toggle = true
                }
            }
        }
    }
    class YourViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val profileImage: ImageView = view.findViewById(R.id.profile_image)
        private val message: TextView = view.findViewById(R.id.message)
        private val date: TextView = view.findViewById(R.id.date)
        private val time: TextView = view.findViewById(R.id.time)

        fun bind(data: YourMessageData) {
            profileImage.setImageResource(data.profileImage)
            message.text = data.message
            date.text = data.date
            time.text = data.time
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val message: TextView = view.findViewById(R.id.message)
        private val date: TextView = view.findViewById(R.id.date)
        private val time: TextView = view.findViewById(R.id.time)

        fun bind(data: MyMessageData) {
            message.text = data.message
            date.text = data.date
            time.text = data.time
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_YOU -> YourViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_your_message, viewGroup, false))
            VIEW_TYPE_ME -> MyViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_my_message, viewGroup, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(viewHolder) {
            is YourViewHolder -> viewHolder.bind(dataSet[position] as YourMessageData)
            is MyViewHolder -> viewHolder.bind(dataSet[position] as MyMessageData)
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int
        = if(dataSet[position] is YourMessageData) VIEW_TYPE_YOU else VIEW_TYPE_ME
}
