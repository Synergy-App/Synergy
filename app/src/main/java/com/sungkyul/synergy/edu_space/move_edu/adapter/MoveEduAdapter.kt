package com.sungkyul.synergy.edu_space.move_edu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentIconListBinding
import com.sungkyul.synergy.edu_space.move_edu.activity.MoveDetailActivity
import com.sungkyul.synergy.edu_space.move_edu.data.Move
import com.sungkyul.synergy.edu_space.move_edu.data.MoveInfo
import com.sungkyul.synergy.utils.DynamicButton

class MoveEduAdapter (private val context: Context, private val moveList: ArrayList<Move>) :
    RecyclerView.Adapter<MoveEduAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentIconListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding, )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(moveList[position])
    }

    override fun getItemCount(): Int {
        return moveList.size
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class CustomViewHolder(private val binding: FragmentIconListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.eduButton.post {
                binding.eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            binding.eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as DynamicButton).startTouchUpAnimation()

                        val moveInfo = when (binding.iconTv.text.toString()) {
                            "터치" -> MoveInfo(
                                R.drawable.ic_word_wifi,
                                "터치",
                                "화면에 손가락을 한번 눌렀다가 떼는 동작을 의미해요."
                            )
                            "스와이프" -> MoveInfo(
                                R.drawable.ic_word_data,
                                "스와이프",
                                "화면에 손가락을 대고, 손가락을 움직여 화면을 이동시키는 것을 의미해요."
                            )
                            "꾹 누르기" -> MoveInfo(
                                R.drawable.ic_word_bluetooth,
                                "꾹 누르기",
                                "화면을 꾹 누르고 있는 것을 의미해요. 터치와는 달리 화면에서 손가락을 뗴지 않고 누르고 있는 상태를 의미해요."
                            )
                            "드래그" -> MoveInfo(
                                R.drawable.ic_wifi_black,
                                "드래그",
                                "화면에서 특정 부분을 손가락으로 누르고 이동시키는 것을 의미행ㅅ, 스와이프와는 달리 화면에서 항목을 선택하고 움직이는 것이에요."
                            )
                            "확대&축소" -> MoveInfo(
                                R.drawable.ic_word_airplane,
                                "확대&축소",
                                "화면을 확대하거나 축소하고 싶을 때 사용할 수 있어요."
                            )
                            else -> MoveInfo(
                                R.drawable.ic_sound_black,
                                "?",
                                "상세 설명이 없습니다."
                            )
                        }
                        val intent = Intent(context, MoveDetailActivity::class.java)
                        intent.putExtra("moveInfo", moveInfo)
                        context.startActivity(intent)
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as DynamicButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        fun bind(move: Move) {
            binding.apply {
                iconIv.setImageResource(move.moveImage) //fragment_ic_list인가 거기에 있는 부분을 수정하는 거라 이렇게 icon이 들어감 move절대 xx
                iconTv.text = move.moveText

            }
        }
    }
}
