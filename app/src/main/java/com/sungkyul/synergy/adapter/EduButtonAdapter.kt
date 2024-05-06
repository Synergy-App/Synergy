package com.sungkyul.synergy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.accountedu.GoogleMainActivity
import com.sungkyul.synergy.edu_space.appinstall.installMainActivity
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.edu_space.delivery.DlvMainActivity
import com.sungkyul.synergy.edu_space.icon_edu.activity.IconEduActivity
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
    val currentProgressRate: Float,
    val maxProgressRate: Float
)

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
        private val title: TextView = itemView.findViewById(R.id.title)
        private val icon: ImageView = itemView.findViewById(R.id.edu_icon)
        private val barChart: HorizontalBarChart = itemView.findViewById(R.id.bar_chart)
        private val eduButton: GalaxyButton = itemView.findViewById(R.id.edu_button)

        init {
            eduButton.post {
                eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }

                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        // 버튼의 제목에 따라 해당 교육 액티비티로 이동한다.
                        when(title.text.toString()) {
                            "아이콘" -> {
                                val intent = Intent(context, IconEduActivity::class.java)
                                context.startActivity(intent)
                            }
                            "화면구성" -> {
                                val intent = Intent(context, ScreenLayoutActivity::class.java)
                                context.startActivity(intent)
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
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        fun bind(buttonItem: EduButtonItem) {
            title.text = buttonItem.buttonText
            icon.setImageResource(buttonItem.imageResId)
            setBarChart(buttonItem)
        }

        // 현재 진행률을 나타내는 바 차트를 설정한다.
        private fun setBarChart(buttonItem: EduButtonItem) {
            // 데이터를 만든다.
            val entries = listOf(BarEntry(0.0f, buttonItem.currentProgressRate))
            val barDataSet = BarDataSet(entries, "progress_rate")
            barDataSet.colors = listOf(Color.rgb(255, 160, 122))
            barDataSet.setDrawValues(false)
            barChart.data = BarData(barDataSet)

            // 값의 범위를 지정한다.
            barChart.axisLeft.axisMinimum = 0.0f
            barChart.axisLeft.axisMaximum = buttonItem.maxProgressRate

            // 불필요한 설명을 제거한다.
            barChart.description.isEnabled = false
            barChart.xAxis.isEnabled = false
            barChart.axisLeft.isEnabled = false
            barChart.axisRight.isEnabled = false
            barChart.legend.isEnabled = false

            // 그리드 배경을 설정한다.
            barChart.setDrawGridBackground(true)
            //barChart.setGridBackgroundColor(Color.GRAY)

            // 애니메이션을 넣는다.
            barChart.animateY(750, Easing.EaseOutSine)
        }
    }
}
