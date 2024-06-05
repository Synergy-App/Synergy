package com.sungkyul.synergy.edu_space.default_app.message.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.default_app.message.activity.DefaultMessageActivity
import com.sungkyul.synergy.utils.GalaxyButton

data class MessageChattingData(
    val profileImage: Int,
    val profileName: String,
    val date: String,
    val recentMessage: String
)

class MessageChattingAdapter(private val context: Context, private val dataSet: ArrayList<MessageChattingData>): RecyclerView.Adapter<MessageChattingAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val galaxyButton: GalaxyButton = view.findViewById(R.id.galaxy_button)
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

        viewHolder.galaxyButton.post { viewHolder.galaxyButton.clipToRect() }

        viewHolder.galaxyButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

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
