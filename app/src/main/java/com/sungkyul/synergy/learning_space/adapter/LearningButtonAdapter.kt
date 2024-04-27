package com.sungkyul.synergy.learning_space.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.adapter.EduButtonItem
import com.sungkyul.synergy.learning_space.intent.LearningDefaultAppActivity
import com.sungkyul.synergy.learning_space.intent.LearningDeliveryActivity
import com.sungkyul.synergy.learning_space.intent.LearningGoogleActivity
import com.sungkyul.synergy.learning_space.intent.LearningIconActivity
import com.sungkyul.synergy.learning_space.intent.LearningInstallActivity
import com.sungkyul.synergy.learning_space.intent.LearningKakaotalkActivity
import com.sungkyul.synergy.learning_space.intent.LearningKakaotaxiActivity
import com.sungkyul.synergy.learning_space.intent.LearningNaverActivity
import com.sungkyul.synergy.learning_space.intent.LearningScreenActivity
import com.sungkyul.synergy.learning_space.intent.LearningSettingActivity
import com.sungkyul.synergy.utils.DynamicButton


class LearningButtonAdapter(private val buttonList: List<EduButtonItem>): RecyclerView.Adapter<LearningButtonAdapter.ButtonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_item_button, parent, false)
        return ButtonViewHolder(view)
    }
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
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
        private val text1: TextView = itemView.findViewById(R.id.learning_tv2) // 이 부분에 해당하는 TextView의 ID를 정확히 입력해야 합니다.
        private val text2: TextView = itemView.findViewById(R.id.learning_tv2)
        private val imageView: ImageView =itemView.findViewById(R.id.edu_icon)
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

                        if (text1.text == "아이콘") {
                            val intent = Intent(itemView.context, LearningIconActivity::class.java)
                            itemView.context.startActivity(intent)
                        } else if (text1.text == "기본앱") {
                            val intent = Intent(itemView.context, LearningDefaultAppActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "화면구성") {
                            val intent = Intent(itemView.context, LearningScreenActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "환경 설정") {
                            val intent = Intent(itemView.context, LearningSettingActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "계정 생성") {
                            val intent = Intent(itemView.context, LearningGoogleActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "앱 설치") {
                            val intent = Intent(itemView.context, LearningInstallActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "카카오톡") {
                            val intent = Intent(itemView.context, LearningKakaotalkActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "네이버") {
                            val intent = Intent(itemView.context, LearningNaverActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "코레일") {
//                            val intent = Intent(itemView.context, ::class.java)
//                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "카카오택시") {
                            val intent = Intent(itemView.context, LearningKakaotaxiActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else if (text1.text == "배달의 민족") {
                            val intent = Intent(itemView.context, LearningDeliveryActivity::class.java)
                            itemView.context.startActivity(intent)
                        }
                        else {

                        }
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
