package com.sungkyul.synergy.edu_space.icon_edu.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.edu_space.icon_edu.data.IconInfo
import com.sungkyul.synergy.databinding.FragmentIconListBinding
import com.sungkyul.synergy.edu_space.icon_edu.activity.IconDetailActivity

/** 교육공간 속 아이콘 리사이클러뷰 어뎁터 */
class IconEduAdapter(private val context: Context, private val iconList: ArrayList<Icon>) :
    RecyclerView.Adapter<IconEduAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentIconListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = iconList[position]
        holder.bind(currentItem)

        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener {
            val iconInfo = when (currentItem.iconText) {
                "신호 없음" -> IconInfo(
                    R.drawable.ic_sound_black,
                    "신호 없음",
                    "신호가 원활하지 않습니다. 핸드폰을 껐다가 다시 실행해 주세요."
                )
                "와이파이" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "와이파이",
                    "무선 인터넷이에요. 와이파이를 켜면, 집이나 공공장소에서 무선으로 인터넷을 사용할 수 있어요. 덕분에 데이터를 아낄 수 있고, 더 빠르게 인터넷을 이용할 수 있답니다."
                )
                "데이터 모바일" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "데이터 모바일",
                    "데이터 LTE, 5G, 3G에 연결됩니다. 와이파이없이 사용하실 수 있습니다."
                )
                "블루투스" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "블루투스",
                    "블루투스에 연결합니다."
                )
                "비행기 탑승 모드" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "비행기 탑승 모드",
                    "비행기에 탑승할 때 이 아이콘을 켜주세요."
                )
                "GPS" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "GPS",
                    "위치 추적을 할 수 있는 아이콘입니다. 길찾기 기능이나 네비게이션기능을 사용하실 때 켜주세요."
                )
                "음성 전화" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "음성 전화",
                    "음성 전화 아이콘입니다."
                )
                "부재중 전화" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "부재중 전화",
                    "부재중 전화 아이콘입니다."
                )
                "문자" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "문자",
                    "문자 아이콘입니다."
                )
                "알림" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "알림",
                    "알림 아이콘입니다."
                )
                "무음 모드" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "무음 모드",
                    "알림 및 모든 소리가 무음으로 되는 아이콘입니다."
                )
                "진동 모드" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "진동 모드",
                    "알림 및 모든 소리가 진동으로 되는 아이콘입니다."
                )"배터리 양 표시" -> IconInfo(
                    R.drawable.ic_wifi_black,
                    "배터리 양 표시",
                    "배터리양을 표시하는 아이콘입니다."
                )
                else -> IconInfo(
                    R.drawable.ic_sound_black,
                    currentItem.iconText,
                    "상세 설명이 없습니다."
                )
            }
            val intent = Intent(context, IconDetailActivity::class.java)
            intent.putExtra("iconInfo", iconInfo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return iconList.size
    }

    inner class CustomViewHolder(private val binding: FragmentIconListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(icon: Icon) {
            binding.apply {
                iconIv.setImageResource(icon.iconImage)
                iconTv.text = icon.iconText

                }
            }
        }
    }
