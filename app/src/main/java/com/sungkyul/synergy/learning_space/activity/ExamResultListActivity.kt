package com.sungkyul.synergy.learning_space.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.learning_space.adapter.ExamResultListAdapter
import com.sungkyul.synergy.learning_space.adapter.ExamResultListData
import com.sungkyul.synergy.utils.GalaxyButton

class ExamResultListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamResultListBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamResultListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = ArrayList<ExamResultListData>()
        for(i in 1..6) {
            dataSet.add(
                ExamResultListData(
                    R.drawable.todo_circle,
                    "1. 문제"
                )
            )
        }

        val recyclerview = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerview.adapter = ExamResultListAdapter(dataSet)

        recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // 스크롤을 시작한 경우
                // 모든 버튼의 터치 애니메이션을 원래대로 되돌린다.
                if(newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    for(i in 0..<dataSet.size) {
                        val galaxyButton = recyclerview.layoutManager?.findViewByPosition(i)?.findViewById<GalaxyButton>(R.id.galaxy_button)
                        if(galaxyButton != null && galaxyButton.getToggle()) {
                            galaxyButton.startTouchUpAnimation()
                        }
                    }
                }
            }
        })

        binding.backButton.post { binding.backButton.clipToRoundRect(27.0f) }
        binding.viewAllButton.post { binding.viewAllButton.clipToRoundRect(27.0f) }

        binding.backButton.setOnTouchListener { view, event ->
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

        binding.viewAllButton.setOnTouchListener { view, event ->
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
}
