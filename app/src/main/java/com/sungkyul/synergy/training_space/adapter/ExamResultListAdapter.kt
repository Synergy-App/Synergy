package com.sungkyul.synergy.training_space.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class ExamResultListData(
    val image: Int,
    val name: String
)

class ExamResultListAdapter(
    private val dataSet: List<ExamResultListData>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ExamResultListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.ox_image)
        val name: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_exam_result, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.image.setImageResource(dataSet[position].image)
        viewHolder.name.text = dataSet[position].name

        viewHolder.image.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount() = dataSet.size
}
