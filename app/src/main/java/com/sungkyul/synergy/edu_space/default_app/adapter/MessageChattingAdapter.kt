package com.sungkyul.synergy.edu_space.default_app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultMessageActivity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DynamicButton

data class MessageChattingData(
    val profileImage: Int,
    val profileName: String,
    val date: String,
    val recentMessage: String
)

class MessageChattingAdapter(private val context: Context, private val dataSet: ArrayList<MessageChattingData>): RecyclerView.Adapter<MessageChattingAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dynamicButton: DynamicButton = view.findViewById(R.id.dynamic_button)
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val profileName: TextView = view.findViewById(R.id.profile_name)
        val date: TextView = view.findViewById(R.id.date)
        val recentMessage: TextView = view.findViewById(R.id.recent_message)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_message_chatting, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.profileImage.setImageResource(dataSet[position].profileImage)
        viewHolder.profileName.text = dataSet[position].profileName
        viewHolder.date.text = dataSet[position].date
        viewHolder.recentMessage.text = dataSet[position].recentMessage

        viewHolder.dynamicButton.post { viewHolder.dynamicButton.clipToRect() }

        viewHolder.dynamicButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as DynamicButton).startTouchUpAnimation()

                    // 메시지 방으로 이동한다.
                    val intent = Intent(context, DefaultMessageActivity::class.java)
                    context.startActivity(intent)
                }
            }
            true
        }
    }

    override fun getItemCount() = dataSet.size
}
