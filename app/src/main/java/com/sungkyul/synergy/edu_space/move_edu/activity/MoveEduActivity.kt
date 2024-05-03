package com.sungkyul.synergy.edu_space.move_edu.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        moveAdapter = MoveEduAdapter(this, moveList) // 여기에 생성한 아이템 리스트를 넣어줍니다.
        recyclerView.adapter = moveAdapter

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
}