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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.adapter.EduButtonItem
import com.sungkyul.synergy.learning_space.intent.*
import com.sungkyul.synergy.utils.GalaxyButton

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
        private val text1: TextView = itemView.findViewById(R.id.title)
        private val text2: TextView = itemView.findViewById(R.id.learning_tv2)
        private val imageView: ImageView = itemView.findViewById(R.id.edu_icon)
        private val eduButton: GalaxyButton = itemView.findViewById(R.id.edu_button)

        init {
            text2.text = "실습"
            eduButton.post {
                eduButton.clipToRoundRect(27.0f)
            }

            eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        val context = itemView.context
                        val activity = context as FragmentActivity
                        val title = text1.text.toString()
                        when (title) {
                            // Intent로 Activity 시작
                            "아이콘" -> context.startActivity(Intent(context, LearningIconActivity::class.java))
                            "기본앱" -> context.startActivity(Intent(context, LearningDefaultAppActivity::class.java))
                            "계정 생성" -> context.startActivity(Intent(context, LearningGoogleActivity::class.java))
                            "앱 설치" -> context.startActivity(Intent(context, LearningInstallActivity::class.java))
                            "카카오톡" -> context.startActivity(Intent(context, LearningKakaotalkActivity::class.java))
                            "네이버" -> context.startActivity(Intent(context, LearningNaverActivity::class.java))
                            "카카오택시" -> context.startActivity(Intent(context, LearningKakaotaxiActivity::class.java))
                            "배달의 민족" -> context.startActivity(Intent(context, LearningDeliveryActivity::class.java))

                            // Fragment 시작
                            "화면구성" -> addFragment(activity, LearningScreenFragment())
                            else -> null
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        private fun addFragment(activity: FragmentActivity, fragment: Fragment) {
            val fragmentManager = activity.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.mainMainFrameLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun bind(buttonItem: EduButtonItem) {
            text1.text = buttonItem.buttonText
            imageView.setImageResource(buttonItem.imageResId)
        }
    }
}
