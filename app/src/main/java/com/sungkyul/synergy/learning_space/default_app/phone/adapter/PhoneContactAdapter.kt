package com.sungkyul.synergy.learning_space.default_app.phone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.edu.EduListener

//연락처 목록을 표시하는 부분
data class ContactData(
    val profile: Int,
    val name: String,
    val phoneNum: String
)

class ContactAdapter(private val dataSet: ArrayList<ContactData>, private val eduListener: EduListener): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profile : ImageView//프로필 표시하는 부분
        val name : TextView//이름
        val phoneNum:TextView
        val itemLayout: LinearLayout
        val hiddenLayout : LinearLayout
        var maxHeight: Int = 0 // 히든 레이아웃의 최대 높이
        var toggle: Boolean = false // 히든 레이아웃 토글 (false이면 접기, true이면 펼치기)

        init {
            profile = view.findViewById(R.id.profile)
            name = view.findViewById(R.id.name)
            phoneNum = view.findViewById(R.id.phone_num_text)
            itemLayout = view.findViewById(R.id.item_layout)
            hiddenLayout = view.findViewById(R.id.contact_hidden_layout)

            itemLayout.background.alpha = 0

            // 히든 레이아웃의 초기 크기를 먼저 측정한다.
            hiddenLayout.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            maxHeight = hiddenLayout.measuredHeight

            hiddenLayout.layoutParams.height = 0

            // 아이템 선택 이벤트
            itemLayout.setOnClickListener {
                when(name.text) {
                    "시너지" -> eduListener.onAction("click_captain_contact_item")
                }

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
            .inflate(R.layout.item_phone_contact, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.profile.setImageResource(dataSet[position].profile)
        viewHolder.name.text = dataSet[position].name
        viewHolder.phoneNum.text = dataSet[position].phoneNum
    }

    override fun getItemCount() = dataSet.size


}