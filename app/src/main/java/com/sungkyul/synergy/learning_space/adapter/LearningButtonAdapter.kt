package com.sungkyul.synergy.learning_space.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelStoreOwner
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
import com.sungkyul.synergy.learning_space.intent.LearningScreenFragment
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
        private val text1: TextView = itemView.findViewById(R.id.title) // 이 부분에 해당하는 TextView의 ID를 정확히 입력해야 합니다.
        private val text2: TextView = itemView.findViewById(R.id.learning_tv2)
        private val imageView: ImageView = itemView.findViewById(R.id.edu_icon)
        private val eduButton: GalaxyButton = itemView.findViewById(R.id.edu_button)

        init {
            text2.text = "실습"
            eduButton.post {
                eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            // MotionEvent.ACTION_UP 안에 기능을 넣어주세요!
            eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        val activity = itemView.context as FragmentActivity
                        val fragment = when (text1.text) {
                            "아이콘" -> LearningIconActivity()
                            "기본앱" -> LearningDefaultAppActivity()
                            "화면구성" -> LearningScreenFragment()
                           // "환경 설정" -> TestFragment()
                            "계정 생성" -> LearningGoogleActivity()
                            "앱 설치" -> LearningInstallActivity()
                            "카카오톡" -> LearningKakaotalkActivity()
                            "네이버" -> LearningNaverActivity()
                            "카카오택시" -> LearningKakaotaxiActivity()
                            "배달의 민족" -> LearningDeliveryActivity()
                            else -> null
                        }
                        fragment?.let {
                            addFragment(activity, it)
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        private fun addFragment(activity: FragmentActivity, fragment: ViewModelStoreOwner) {
            val fragmentManager = activity.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.mainMainFrameLayout, fragment as Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun bind(buttonItem: EduButtonItem) {
            text1.text = buttonItem.buttonText
            imageView.setImageResource(buttonItem.imageResId)
        }
    }
}
