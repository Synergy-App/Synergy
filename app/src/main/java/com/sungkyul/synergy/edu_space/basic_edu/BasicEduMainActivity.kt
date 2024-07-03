package com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.dictionary_edu.activity.IconEduFragment
import com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.move_edu.activity.MoveEduFragment

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
            addFragment(requireActivity(), IconEduFragment())
        }

        setDynamicTextSize(view) // 추가: 텍스트 크기 설정

        return view
    }


    private fun getScreenSize(): Point {
        val display = (requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize(): Pair<Int, Int> {
        val screenSize = getScreenSize()
        val density = resources.displayMetrics.density

        val standardSizeX = (screenSize.x / density).toInt()
        val standardSizeY = (screenSize.y / density).toInt()

        return Pair(standardSizeX, standardSizeY)
    }

    private fun setDynamicTextSize(view: View) {
        val (standardSizeX, standardSizeY) = getStandardSize()

        // 각각의 텍스트 요소에 다른 크기 설정
        val basicEdu: TextView = view.findViewById(R.id.basic_edu)
        basicEdu.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 12).toFloat())

        val basicEduSub: TextView = view.findViewById(R.id.basic_edu_sub)
        basicEduSub.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        // headerImage의 높이 설정
        val header: View = view.findViewById(R.id.header)
        val headerHeight = (standardSizeY * 0.6).toInt() // 높이를 화면 높이의 10%로 설정
        header.layoutParams.height = headerHeight
        header.requestLayout()
    }


    private fun addFragment(activity: FragmentActivity, fragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.mainMainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}