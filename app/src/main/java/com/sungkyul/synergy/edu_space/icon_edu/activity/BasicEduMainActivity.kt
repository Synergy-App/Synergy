package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.move_edu.fragment.MoveEduFragment

class BasicEduMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_basicedu_main, container, false)

        val basicMoveButton = view.findViewById<Button>(R.id.basic_move_btn)
        val wordDicButton = view.findViewById<Button>(R.id.word_dic_btn)

        basicMoveButton.setOnClickListener {
            // 기본 동작 교육 버튼 클릭 이벤트 처리
            addFragment(requireActivity(), MoveEduFragment())

        }

        wordDicButton.setOnClickListener {
            // 용어 사전 버튼 클릭 이벤트 처리
            val intent = Intent(activity, IconEduActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun addFragment(activity: FragmentActivity, fragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.mainMainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}