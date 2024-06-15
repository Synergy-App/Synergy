package com.sungkyul.synergy.my_profile

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.learning_space.activity.ExamResultListActivity

class ExamResultAdapter(
    private val examResults: List<ExamResult>,
    private val educationId: Int,
    private val totalQuestions: Int
) : RecyclerView.Adapter<ExamResultAdapter.ExamResultViewHolder>() {

    class ExamResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExamTitle: TextView = itemView.findViewById(R.id.textViewExamTitle)
        val textViewExamTitle2: TextView = itemView.findViewById(R.id.textViewExamTitle2)
        val textViewExamResult: TextView = itemView.findViewById(R.id.textViewExamResult)
        val imageViewExamIcon: ImageView = itemView.findViewById(R.id.imageViewExamIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result_exam, parent, false)
        return ExamResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExamResultViewHolder, position: Int) {
        val currentItem = examResults[position]
        holder.textViewExamTitle.text = currentItem.title
        holder.textViewExamTitle2.text = currentItem.title2
        holder.textViewExamResult.text = currentItem.result
        holder.imageViewExamIcon.setImageResource(currentItem.imageResId)

        holder.imageViewExamIcon.setOnClickListener {
            Log.d("ExamResultAdapter", "ImageView clicked for position: $position")
            val context = holder.itemView.context
            val intent = Intent(context, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", ArrayList(currentItem.resultList))
                putExtra("educationId", educationId)
                putExtra("totalQuestions", totalQuestions)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = examResults.size
}
