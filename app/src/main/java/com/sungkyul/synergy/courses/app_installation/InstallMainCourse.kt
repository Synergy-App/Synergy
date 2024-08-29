package com.sungkyul.synergy.courses.app_installation

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class InstallMainCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "만약 로그인이 되어 있다면<br>바로 메인 화면이 뜨고"
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f/930.0f
            dialog.bottom = 700.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "로그인이 되어 있지 않다면<br>구글 로그인 창으로<br>연결됩니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "계정을 만드는 방법은<br>\"계정 생성 교육\"을<br>참고해주세요."
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "구글 로그인이<br>되어 있는 상태에서<br>교육을 진행하겠습니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "이 부분은<br>검색창입니다."
            dialog.top = 90.0f/930.0f
            dialog.bottom = 650.0f/930.0f
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 20.0f/412.0f
            cover.boxRight = (412.0f-180.0f)/412.0f
            cover.boxTop = 5.0f/930.0f
            cover.boxBottom = 70.0f/930.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "내가 설치하기 위한<br>앱의 이름을 검색하면 됩니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "카카오톡을<br>설치해 볼까요?"
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            action.id = "search_kakao"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 50.0f/412.0f,
                    y = 50.0f/930.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
