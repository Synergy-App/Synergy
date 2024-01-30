package com.sungkyul.synergy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.IconListBinding

/**교육공간 속 아이콘 리사이클러뷰 어뎁터 */
class IconEduAdapter(val iconList:ArrayList<Icon>):RecyclerView.Adapter<IconEduAdapter.CustomViewHolder>(){
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
    inner class CustomViewHolder(private val binding: IconListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(icon: Icon) {
            binding.apply {
                iconIv.setImageResource(icon.iconImage) // 이미지 설정
                iconTv.text = icon.iconText // 텍스트 설정

                root.setOnClickListener {
                    val context = root.context
                    Toast.makeText(context, "아이콘 텍스트: ${icon.iconText}", Toast.LENGTH_SHORT).show()
                    /**여기서 클릭했을 떄 토스트 메세지가 아닌 화면 전환!!*/
                }
            }
        }
    }
}