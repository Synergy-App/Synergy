package com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.move_edu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.move_edu.data.Move
import com.sungkyul.synergy.databinding.FragmentIconListBinding
import com.sungkyul.synergy.learning_space.basic_edu.move_edu.activity.MoveDetailActivity
import com.sungkyul.synergy.utils.GalaxyButton

class MoveEduAdapter(
    private val context: Context,
    private val moveList: ArrayList<Move>,
    private val allMoveList: ArrayList<Move>,
    private val standardSize_X: Int // 추가된 부분
) : RecyclerView.Adapter<MoveEduAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentIconListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(moveList[position], position)
    }

    override fun getItemCount(): Int {
        return moveList.size
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class CustomViewHolder(private val binding: FragmentIconListBinding) : RecyclerView.ViewHolder(binding.root) {
        private var currentIndex = 0

        init {
            binding.eduButton.post {
                binding.eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            binding.eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        val intent = Intent(context, MoveDetailActivity::class.java)
                        intent.putExtra("moveList", allMoveList)
                        intent.putExtra("currentIndex", currentIndex)
                        Log.i("move-edu-adapter-touch", currentIndex.toString())
                        context.startActivity(intent)
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        fun bind(move: Move, position: Int) {
            currentIndex = move.currentIndex
            Log.i("move-edu-adapter-bind", currentIndex.toString())

            binding.apply {
                iconIv.setImageResource(move.moveImage)
                iconTv.text = move.moveText

                // 텍스트 크기 설정
                iconTv.textSize = (standardSize_X / 15).toFloat()

                // 아이콘 크기 조정
                val newWidth = standardSize_X / 5.5
                val newHeight = standardSize_X / 5.5

                val params = iconIv.layoutParams as ViewGroup.LayoutParams
                params.width = (newWidth * context.resources.displayMetrics.density).toInt()
                params.height = (newHeight * context.resources.displayMetrics.density).toInt()
                iconIv.layoutParams = params

                // 박스 크기 조정
                val displayMetrics: DisplayMetrics = context.resources.displayMetrics
                val boxHeight = standardSize_X / 3.5
                val layoutParams = itemView.layoutParams
                layoutParams.height = (boxHeight * displayMetrics.density).toInt()
                itemView.layoutParams = layoutParams
            }
        }
    }
}
