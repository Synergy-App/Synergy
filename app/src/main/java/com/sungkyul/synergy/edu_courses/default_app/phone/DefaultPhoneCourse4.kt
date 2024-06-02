package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

class DefaultPhoneCourse4 (val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())
    //연락처 추가
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "연락처 저장에<br>성공하였습니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 600.0f
            dialog.bottom = 150.0f
            dialog.start = 24.0f
            dialog.end = 24.0f

            cover.boxLeft = 10.0f
            cover.boxTop = 800.0f
            cover.boxRight = width-10.0f
            cover.boxBottom = 700.0f /*크기가 커질 수록 박스가 커짐 */

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_yellow_bg
            cover.isClickable = true

        })

        list.add(EduData().apply {
            dialog.contentText = "만약 저장된 연락처가 너무 많아<br>찾고 싶은 사람의 연락처를<br>한번에 확인하기 어려울 때는<br>상대방의 전화번호를 저장할 수 있고<br>상대방에게 전화를 걸 수 있습니다."
            dialog.contentSize = 24.0f
            dialog.top = 300.0f
            dialog.bottom = 250.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })
    }
}