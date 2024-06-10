package com.sungkyul.synergy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.accountedu.GoogleMainActivity
import com.sungkyul.synergy.edu_space.appinstall.AppInstallMainActivity
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.edu_space.delivery.DlvMainActivity
import com.sungkyul.synergy.edu_space.icon_edu.activity.BasicEduMainFragment
import com.sungkyul.synergy.edu_space.kakaotalk.activity.KakaoMainActivity
import com.sungkyul.synergy.edu_space.kakaotaxi.TaxiMainActivity
import com.sungkyul.synergy.edu_space.naver.activity.NaverActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenLayoutActivity
import com.sungkyul.synergy.edu_space.settingedu2.Setting2MainActivity
import com.sungkyul.synergy.edu_space.ticket.TicketMainActivity
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

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttonList[position])
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val icon: ImageView = itemView.findViewById(R.id.edu_icon)
        private val eduButton: GalaxyButton = itemView.findViewById(R.id.edu_button)

        init {
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

                        when (title.text.toString()) {
                            "기초" -> {
                                val fragment = BasicEduMainFragment()
                                fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container_view, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }
                            "화면구성" -> {
                                val intent = Intent(context, ScreenLayoutActivity::class.java)
                                context.startActivity(intent)
                            }
                            "기본 앱" -> {
                                val intent = Intent(context, DefaultAppActivity::class.java)
                                context.startActivity(intent)
                            }
                            "환경 설정" -> {
                                val intent = Intent(context, Setting2MainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "계정 생성" -> {
                                val intent = Intent(context, GoogleMainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "앱 설치" -> {
                                val intent = Intent(context, AppInstallMainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "카카오톡" -> {
                                val intent = Intent(context, KakaoMainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "네이버" -> {
                                val intent = Intent(context, NaverActivity::class.java)
                                context.startActivity(intent)
                            }
                            "코레일" -> {
                                val intent = Intent(context, TicketMainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "카카오택시" -> {
                                val intent = Intent(context, TaxiMainActivity::class.java)
                                context.startActivity(intent)
                            }
                            "배달의 민족" -> {
                                val intent = Intent(context, DlvMainActivity::class.java)
                                context.startActivity(intent)
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

        fun bind(buttonItem: EduButtonItem) {
            title.text = buttonItem.buttonText
            icon.setImageResource(buttonItem.imageResId)
        }
    }
}
