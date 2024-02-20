package com.sungkyul.synergy.training.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.adapter.EduButtonAdapter
import com.sungkyul.synergy.data.EduButtonItem
import com.sungkyul.synergy.training.data.TrainingButtomItem

class TrainingButtonAdapter (private val buttonList: List<TrainingButtomItem>) :
    RecyclerView.Adapter<TrainingButtonAdapter.ButtonViewHolder>() {

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

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layout1: LinearLayout = itemView.findViewById(R.id.learning_layout)
        private val text1: TextView = itemView.findViewById(R.id.learning_tv) // 이 부분에 해당하는 TextView의 ID를 정확히 입력해야 합니다.
        private val imageView: ImageView =itemView.findViewById(R.id.edu_icon)
        fun bind(buttonItem: TrainingButtomItem) {
            text1.text = buttonItem.buttonText
            imageView.setImageResource(buttonItem.imageResId)
        }
    }
}