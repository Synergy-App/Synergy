package com.sungkyul.synergy.adapter

import android.annotation.SuppressLint
import android.content.Context
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
import com.sungkyul.synergy.edu_space.accountedu.GoogleMainActivity
import com.sungkyul.synergy.edu_space.appinstall.installMainActivity
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultAppActivity
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultPhoneActivity
import com.sungkyul.synergy.edu_space.delivery.DlvMainActivity
import com.sungkyul.synergy.edu_space.kakaotalk.activity.KakaoMainActivity
import com.sungkyul.synergy.edu_space.kakaotaxi.TaxiMainActivity
import com.sungkyul.synergy.edu_space.naver.activity.NaverActivity
import com.sungkyul.synergy.edu_space.settingedu2.Setting2MainActivity
import com.sungkyul.synergy.edu_space.ticket.TicketMainActivity
import com.sungkyul.synergy.utils.DynamicButton

class EduButtonAdapter(private val context: Context, private val buttonList: List<EduButtonItem>): RecyclerView.Adapter<EduButtonAdapter.ButtonViewHolder>() {
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

                        // 버튼의 제목에 따라 해당 교육 액티비티로 이동한다.
                        when(text1.text.toString()) {
                            "아이콘" -> {
                                /*val intent = Intent(context, DefaultAppActivity::class.java)
                                context.startActivity(intent)*/
                            }
                            "화면구성" -> {
                                /*val intent = Intent(context, DefaultAppActivity::class.java)
                                context.startActivity(intent)*/
                            }
                            "기본앱" -> {
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
                                val intent = Intent(context, installMainActivity::class.java)
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
