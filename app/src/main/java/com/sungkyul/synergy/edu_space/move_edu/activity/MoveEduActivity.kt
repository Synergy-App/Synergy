package com.sungkyul.synergy.edu_space.move_edu.activity

import android.graphics.Point
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.input.pointer.PointerEventType.Companion.Move
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityMoveEduBinding
import com.sungkyul.synergy.edu_space.move_edu.adapter.MoveEduAdapter
import com.sungkyul.synergy.edu_space.move_edu.data.Move


class MoveEduActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoveEduBinding
    private lateinit var moveAdapter: MoveEduAdapter // MoveEduAdapter에 해당하는 부분입니다.
    private var standardSize_X = 0
    private var standardSize_Y = 0
    private var density = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 화면의 기준 사이즈를 계산
        getStandardSize()

        // 텍스트 크기를 기준 사이즈를 이용해 설정
        binding.iconeduTool.textSize = (standardSize_X / 12).toFloat()
        binding.iconeduTool2.textSize = (standardSize_X / 20).toFloat()
        binding.searchEditText.textSize = (standardSize_X / 20).toFloat()

        val recyclerView: RecyclerView = binding.moveRv // XML에서 정의한 RecyclerView의 ID를 가져옵니다.

        // RecyclerView에 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager 또는 필요한 레이아웃 매니저를 사용합니다.



        val iconSearchDict = listOf(
            Pair(R.drawable.ic_move_touch, "터치"),
            Pair(R.drawable.ic_move_swipe, "스와이프"),
            Pair(R.drawable.ic_move_push, "꾹 누르기"),
            Pair(R.drawable.ic_move_drag, "드래그"),
            Pair(R.drawable.ic_move_size, "확대&축소"),
            Pair(R.drawable.ic_sound_black, "캡처"),
        )

        val moveList = ArrayList<Move>()
        for(i in iconSearchDict) {
            moveList.add(Move(i.first, i.second))
        }

        moveAdapter = MoveEduAdapter(this, moveList,  standardSize_X) // 여기에 생성한 아이템 리스트를 넣어줍니다.
        recyclerView.adapter = moveAdapter

        //검색창 기능
        binding.searchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapter = recyclerView.adapter as MoveEduAdapter

                // 모든 아이템들을 삭제한다.
                adapter.notifyItemRangeRemoved(0, moveList.size)

                // 자동 완성
                moveList.clear()
                if(s.toString().isNotEmpty()) {
                    for (i in iconSearchDict.filter { it.second.contains(s.toString()) }) {
                        moveList.add(Move(i.first, i.second))
                    }
                } else {
                    // 검색 창이 비어 있으면, 모든 아이템들을 채운다.
                    for(i in iconSearchDict) {
                        moveList.add(Move(i.first, i.second))
                    }
                }

                // 아이템들을 추가한다.
                adapter.notifyItemRangeInserted(0, moveList.size)
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    // 디스플레이 사이즈를 반환하는 메서드
    private fun getScreenSize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    // 기기의 해상도와 밀도를 기준으로 기준 사이즈를 계산하는 메서드
    private fun getStandardSize() {
        val screenSize = getScreenSize()
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        density = displayMetrics.density

        standardSize_X = (screenSize.x / density).toInt()
        standardSize_Y = (screenSize.y / density).toInt()
    }
}