package com.sungkyul.synergy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultAppBinding
import com.sungkyul.synergy.edu_space.accountedu.GoogleMainActivity
import com.sungkyul.synergy.edu_space.appinstall.AppInstallMainActivity
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.edu_space.icon_edu.activity.BasicEduMainFragment
import com.sungkyul.synergy.edu_space.kakaotalk.activity.KakaoMainActivity
import com.sungkyul.synergy.edu_space.naver.activity.NaverActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenLayoutActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenLockActivity
import com.sungkyul.synergy.edu_space.settingedu2.Setting2MainActivity
import com.sungkyul.synergy.utils.GalaxyButton

data class EduButtonItem(
    val buttonText: String,
    val imageResId: Int,
)

class EduButtonAdapter(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private val buttonList: List<EduButtonItem>
) : RecyclerView.Adapter<EduButtonAdapter.ButtonViewHolder>() {

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
            text2.text = "교육"
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

                        // 버튼의 제목에 따라 해당 교육 액티비티로 이동한다.
                        when(text1.text.toString()) {
                            "기초" -> {
                                /*val intent = Intent(context, BasicEduMainActivity::class.java)
                                context.startActivity(intent)*/
                                addFragment(context as FragmentActivity, BasicEduMainFragment())
                            }
                            "화면구성" -> {
                                val intent = Intent(context, ScreenLockActivity::class.java)
                                context.startActivity(intent)
                            }
                            "카메라" -> {
                                /* val intent = Intent(context, DefaultAppActivity::class.java)
                                 context.startActivity(intent)*/
                            }
                            "앨범" -> {
                                /* val intent = Intent(context, DefaultAppActivity::class.java)
                                 context.startActivity(intent)*/
                            }
                            "전화" -> {
                                /*val intent = Intent(context, DefaultAppActivity::class.java)
                                context.startActivity(intent)*/
                            }
                            "문자" -> {
                                /* val intent = Intent(context, DefaultAppActivity::class.java)
                                 context.startActivity(intent)*/
                            }
                            "환경 설정" -> {
                                /* val intent = Intent(context, Setting2MainActivity::class.java)
                                 context.startActivity(intent)*/
                            }
                            "계정 생성" -> {
                                /*  val intent = Intent(context, GoogleMainActivity::class.java)
                                  context.startActivity(intent)*/
                            }
                            "앱 설치" -> {
                                /*val intent = Intent(context, AppInstallMainActivity::class.java)
                                context.startActivity(intent)*/
                            }
                            "카카오톡" -> {
                                /*  val intent = Intent(context, KakaoMainActivity::class.java)
                                  context.startActivity(intent)*/
                            }
                            "네이버" -> {
                                /*val intent = Intent(context, NaverActivity::class.java)
                                context.startActivity(intent)*/
                            }
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        private fun getScreenSize(): Point {
            val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
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
