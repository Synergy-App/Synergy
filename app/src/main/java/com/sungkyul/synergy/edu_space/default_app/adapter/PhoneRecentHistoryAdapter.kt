package com.sungkyul.synergy.edu_space.default_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AnimUtils

data class RecentHistoryData(
    val profile: Int,
    val statusImage: Int,
    val mic: Int,
    val name: String,
    val statusName: String,
    val time: String,
    val phoneNum: String
)

class RecentHistoryAdapter(private val dataSet: ArrayList<RecentHistoryData>): RecyclerView.Adapter<RecentHistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profile: ImageView
        val statusImage: ImageView
        val mic: ImageView
        val name: TextView
        val statusName: TextView
        val time: TextView
        val phoneNum: TextView
        val itemLayout: LinearLayout
        val hiddenLayout: LinearLayout

        var maxHeight: Int = 0 // 히든 레이아웃의 최대 높이
        var toggle: Boolean = false // 히든 레이아웃 토글 (false이면 접기, true이면 펼치기)

        init {
            profile = view.findViewById(R.id.profile)
            statusImage = view.findViewById(R.id.status_image)
            mic = view.findViewById(R.id.mic)
            name = view.findViewById(R.id.name)
            statusName = view.findViewById(R.id.status_name)
            time = view.findViewById(R.id.time)
            phoneNum = view.findViewById(R.id.phone_num_text)
            itemLayout = view.findViewById(R.id.item_layout)
            hiddenLayout = view.findViewById(R.id.hidden_layout)

            itemLayout.background.alpha = 0

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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_phone_recent_history, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.profile.setImageResource(dataSet[position].profile)
        viewHolder.statusImage.setImageResource(dataSet[position].statusImage)
        viewHolder.mic.setImageResource(dataSet[position].mic)
        viewHolder.name.text = dataSet[position].name
        viewHolder.statusName.text = dataSet[position].statusName
        viewHolder.time.text = dataSet[position].time
        viewHolder.phoneNum.text = dataSet[position].phoneNum
    }

    override fun getItemCount() = dataSet.size
}
