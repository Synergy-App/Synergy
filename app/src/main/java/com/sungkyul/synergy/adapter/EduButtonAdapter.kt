package com.sungkyul.synergy.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.data.EduButtonItem
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultPhoneActivity
import com.sungkyul.synergy.utils.DynamicButton

class EduButtonAdapter(private val buttonList: List<EduButtonItem>): RecyclerView.Adapter<EduButtonAdapter.ButtonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_item_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttonList[position])
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layout1: LinearLayout = itemView.findViewById(R.id.learning_layout)
        private val text1: TextView = itemView.findViewById(R.id.learning_tv) // 이 부분에 해당하는 TextView의 ID를 정확히 입력해야 합니다.
        private val imageView:ImageView=itemView.findViewById(R.id.edu_icon)
        private val eduButton: DynamicButton = itemView.findViewById(R.id.edu_button)

        init {
            eduButton.post {
                eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            // MotionEvent.ACTION_UP 안에 기능을 넣어주세요!
            eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as DynamicButton).startTouchUpAnimation()
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as DynamicButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        fun bind(buttonItem: EduButtonItem) {
            text1.text = buttonItem.buttonText
            imageView.setImageResource(buttonItem.imageResId)
        }
    }
}
