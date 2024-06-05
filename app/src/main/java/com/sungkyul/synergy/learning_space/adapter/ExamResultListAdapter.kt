package com.sungkyul.synergy.learning_space.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GalaxyButton

data class ExamResultListData(
    val image: Int,
    val name: String
)

// 시험 결과 리스트 어댑터
class ExamResultListAdapter(private val dataSet: ArrayList<ExamResultListData>): RecyclerView.Adapter<ExamResultListAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.ox_image)
        val name: TextView = view.findViewById(R.id.name)
        val galaxyButton: GalaxyButton = view.findViewById(R.id.galaxy_button)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_exam_result, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.image.setImageResource(dataSet[position].image)
        viewHolder.name.text = dataSet[position].name

        viewHolder.galaxyButton.post { viewHolder.galaxyButton.clipToRoundRect(27.0f) }

        viewHolder.galaxyButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    //val intent = Intent(requireContext(), DefaultPhoneActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }
    }

    override fun getItemCount() = dataSet.size
}
