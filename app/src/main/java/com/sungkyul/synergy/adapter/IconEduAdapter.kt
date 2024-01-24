package com.sungkyul.synergy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.data.Icon
import com.sungkyul.synergy.data.IconInfo
import com.sungkyul.synergy.databinding.IconListBinding
import com.sungkyul.synergy.IconEdu.IconDetailActivity

/**교육공간 속 아이콘 리사이클러뷰 어뎁터 */
class IconEduAdapter(val context: Context,  val iconList:ArrayList<Icon>):RecyclerView.Adapter<IconEduAdapter.CustomViewHolder>() {
    //화면을 최초 로딩하여 만들어지는 View가 없는 경우 inflate 하여 뷰 홀더 생성
    //CustomViewHolder를 생성하고 setOnClick 사용하자.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): IconEduAdapter.CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IconListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    //생성된 Holder에서 보관중인 뷰(iconText)들을 데이터인 iconList 와 연결
    override fun onBindViewHolder(holder: IconEduAdapter.CustomViewHolder, position: Int) {
        val currentItem = iconList[position]
        holder.bind(currentItem)
    }

    //아이템 리스트의 총 갯수 리턴
    override fun getItemCount(): Int {
        return iconList.size
    }

    //holder에서 보관해야할 View(iconText)들을 변수에 담아둔다.
    inner class CustomViewHolder(private val binding: IconListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(icon: Icon) {
            binding.apply {
                iconIv.setImageResource(icon.iconImage) // 이미지 설정
                iconTv.text = icon.iconText // 텍스트 설정

                root.setOnClickListener {
                    val iconInfo = when (icon.iconText) {
                        "음량 조절" -> IconInfo(R.drawable.sound, "음량 조절", "음량을 조절합니다.")
                        "와이파이" -> IconInfo(R.drawable.sound, "와이파이", "와이파이를 설정합니다.")
                        "손전등" -> IconInfo(R.drawable.sound, "손전등", "손전등을 켭니다.")
                        "블루투스" -> IconInfo(R.drawable.sound, "블루투스", "블루투스에 대한 설명.")
                        "비행기모드" -> IconInfo(R.drawable.sound, "비행기모드", "비행기모드에 대한 설명.")
                        "절전모드" -> IconInfo(R.drawable.sound, "절전모드", "절전모드에 대한 설명.")
                        "데이터모바일" -> IconInfo(R.drawable.sound, "데이터모바일", "데이터모바일에 대한 설명.")
                        "화면녹화" -> IconInfo(R.drawable.sound, "화면녹화", "화면녹화에 대한 설명.")
                        "다크모드" -> IconInfo(R.drawable.sound, "다크모드", "다크모드에 대한 설명.")
                        "빅스비 루틴" -> IconInfo(R.drawable.sound, "빅스비 루틴", "빅스비 루틴에 대한 설명.")
                        // 추가적인 아이콘 정보도 필요한 대로 추가하세요
                        else -> IconInfo(R.drawable.sound, icon.iconText, "상세 설명이 없습니다.")
                    }

                    val intent = Intent(context, IconDetailActivity::class.java)
                    intent.putExtra("iconInfo", iconInfo)
                    intent.putExtra("currentIconIndex", adapterPosition) // 현재 아이콘의 인덱스 전달
                    context.startActivity(intent)
                   /* val context = root.context

                    // 음량 조절 아이템이 클릭되었을 때
                    if (icon.iconText == "음량 조절") {
                        // 다음 화면으로 전환하는 코드
                        val intent = Intent(context, IconDetailActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        // 다른 아이템에 대한 클릭 처리

                        Toast.makeText(context, "아이콘 텍스트: ${icon.iconText}", Toast.LENGTH_SHORT)
                            .show()
                        /**여기서 클릭했을 떄 토스트 메세지가 아닌 화면 전환!!*/
                        *?/
                    */

                }
            }
        }
    }
}