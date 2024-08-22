package com.sungkyul.synergy.training_space.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Point
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.activity.ChooseTypeActivity
import com.sungkyul.synergy.utils.GalaxyButton


data class LearningButtonItem(
    val buttonText: String,
    val imageResId: Int,
    val educationId: Int // 추가된 필드
)
class LearningButtonAdapter(private val buttonList: List<LearningButtonItem>) :
    RecyclerView.Adapter<LearningButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_item_button, parent, false)
        return ButtonViewHolder(view, parent.context)
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
    inner class ButtonViewHolder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val layout1: LinearLayout = itemView.findViewById(R.id.learning_layout)
        private val text1: TextView = itemView.findViewById(R.id.title)
        private val text2: TextView = itemView.findViewById(R.id.learning_tv2)
        private val imageView: ImageView = itemView.findViewById(R.id.edu_icon)
        private val eduButton: GalaxyButton = itemView.findViewById(R.id.edu_button)
        private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)

        init {
            text2.text = "실습"
            eduButton.post {
                eduButton.clipToRoundRect(27.0f)
            }

            // 화면 크기에 따른 텍스트 크기 설정
            setDynamicTextSize()

            eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }

                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        // 아이템 클릭 이벤트 처리
                        handleItemClick()
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }

            // 추가된 itemView 클릭 이벤트 처리
            itemView.setOnClickListener {
                // 아이템 클릭 시 이벤트 처리
                handleItemClick()
            }
        }

        private fun handleItemClick() {
            val activity = itemView.context as FragmentActivity

            // 버튼 아이템에서 educationId를 가져옴
            val educationId = (itemView.tag as LearningButtonItem).educationId

            Log.d("LearningButtonAdapter", "Received ID for ${text1.text}: $educationId")

            val editor = sharedPreferences.edit()
            editor.putInt("receivedId", educationId)
            editor.apply()

            Log.d("LearningButtonAdapter", "Saved receivedId to SharedPreferences: $educationId")

            val intent = Intent(activity, ChooseTypeActivity::class.java)
            activity.startActivity(intent)
        }

        private fun getScreenSize(): Point {
            val display =
                (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            val size = Point()
            display.getSize(size)
            return size
        }

        private fun getStandardSize(): Pair<Int, Int> {
            val screenSize = getScreenSize()
            val density = context.resources.displayMetrics.density

            val standardSizeX = (screenSize.x / density).toInt()
            val standardSizeY = (screenSize.y / density).toInt()

            return Pair(standardSizeX, standardSizeY)
        }

        private fun setDynamicTextSize() {
            val (standardSizeX, standardSizeY) = getStandardSize()

            // 각각의 텍스트 요소에 다른 크기 설정
            text1.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 14).toFloat())
            text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
        }

        fun bind(buttonItem: LearningButtonItem) {
            text1.text = buttonItem.buttonText
            imageView.setImageResource(buttonItem.imageResId)
            itemView.tag = buttonItem // itemView에 버튼 아이템을 태그로 저장
        }
    }
}
