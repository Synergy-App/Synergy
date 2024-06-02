package com.sungkyul.synergy.edu_space.default_app.message.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.default_app.message.activity.DefaultMessageActivity
import com.sungkyul.synergy.edu_space.default_app.message.activity.DefaultMessageFristActivity
import com.sungkyul.synergy.edu_space.default_app.message.activity.DefaultMessageSelectActivity
import com.sungkyul.synergy.utils.edu.EduListener

//메세지 연락처 데이터
data class MessageContactData(
    val Mprofile: Int,
    val name: String,
    val phoneNum: String
)
class MessageSelectAdapter (private val dataSet: ArrayList<MessageContactData>, private val eduListener: EduListener,  private val context: Context): RecyclerView.Adapter<MessageSelectAdapter.ViewHolder>() {// private val 변수를 선언하는 거
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Mprofile: ImageView//프로필 표시하는 부분
        val name: TextView//이름
        val phoneNum: TextView
        val itemLayout: LinearLayout

        init {
            Mprofile = view.findViewById(R.id.Mprofile)
            name = view.findViewById(R.id.name)
            phoneNum = view.findViewById(R.id.phoneNum)
            itemLayout = view.findViewById(R.id.item_layout)

            // 아이템 선택 이벤트
            itemLayout.setOnClickListener {
                when (name.text) {
                    "대장님" ->if(
                        eduListener.onAction("click_captain_contact_item")){
                        val intent = Intent(context, DefaultMessageFristActivity::class.java)
                        context.startActivity(intent)
                    }

                }

            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_message_select, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MessageSelectAdapter.ViewHolder, position: Int) {
        viewHolder.Mprofile.setImageResource(dataSet[position].Mprofile)
        viewHolder.name.text = dataSet[position].name
        viewHolder.phoneNum.text = dataSet[position].phoneNum
    }

    override fun getItemCount() = dataSet.size


}