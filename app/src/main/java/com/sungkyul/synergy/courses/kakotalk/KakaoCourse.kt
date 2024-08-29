package com.sungkyul.synergy.courses.kakotalk

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class KakaoCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "카카오톡의<br>메인 화면입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.6f
            dialog.bottom = 0.2f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            //cover.visibility = true
            cover.isClickable = true
            dialog.contentSize=AdaptiveUtils.dialogContentMedium()
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentColor=R.color.white
            dialog.contentFont = R.font.pretendard_semibold
        })

        list.add(EduData().apply {
            dialog.top = 0.35f
            dialog.bottom = 0.35f
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor=R.color.black
            dialog.contentText = "앱을 실행하게 되면<br>가장 먼저 뜨는 화면이자<br>내 프로필과 친구 목록이<br>뜨는 화면입니다."
            cover.visibility=true
        })

        list.add(EduData().apply {
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxLeft = 0.0f
            cover.boxTop = 0.05f
            cover.boxRight = 1.0f
            cover.boxBottom = 0.175f
            dialog.top = 0.2f
            dialog.bottom = 0.6f
            dialog.contentText = "내 프로필 입니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "프로필이란<br>카카오톡을 사용할 때<br>다른 사람들에게<br>내가 누군지 알려주는<br>내 정보입니다."
            dialog.top = 0.325f
            dialog.bottom = 0.325f

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.contentText = "나의 친구 목록입니다."
            dialog.top = 0.3f
            dialog.bottom = 0.5f
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxBorderColor=R.color.black
            cover.boxTop = 0.5f
            cover.boxBottom = 0.8f
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.contentText = "연락처에 저장된 상대가<br>카카오톡을 한다면"
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            cover.boxVisibility=false
            cover.boxBorderVisibility=false
        })

        list.add(EduData().apply {
            dialog.contentText = "친구 목록에 자동으로<br>뜨게 됩니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "친구를 찾을 수 있는<br>버튼입니다."
            dialog.top = 0.1f
            dialog.bottom = 0.7f
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxBorderColor=R.color.lime
            cover.boxLeft = 0.625f
            cover.boxTop = 0.025f
            cover.boxRight = 0.7f
            cover.boxBottom = 0.06f
            arrow.endTo = EduScreen.BOX
        })

        list.add(EduData().apply {
            dialog.contentText = "카카오톡으로<br>친구와 카톡을<br>주고 받아 볼까요?"
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.background=R.drawable.edu_dialog_green_bg
            dialog.contentColor=R.color.white
            cover.boxVisibility=false
            cover.boxBorderVisibility=false

            bottomDialog.visibility = true
        })

        list.add(EduData().apply {
            dialog.top = 0.1f
            dialog.bottom = 0.7f
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black

            bottomDialog.height = 0.3f
            bottomDialog.contentSize = AdaptiveUtils.dialogContentMedium()
            bottomDialog.contentGravity = Gravity.CENTER
            bottomDialog.contentFont = R.font.pretendard_semibold
            bottomDialog.contentText = "<p style='color: red'><b>여기서 잠깐!</b></p><br>많은 사람들이 카카오톡을<br>\"카톡\"으로 줄여서 부르곤 합니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "<p style='color: red'><b>여기서 잠깐!</b></p><br>그래서 카카오톡으로 메시지를<br>보낼 때는 \"카톡\"을 보냈다고<br>표현을 하기도 해요!"
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            bottomDialog.visibility=false
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.5f,
                    y = 0.2f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })



        /*
        list.add(EduData().apply {
            dialog.contentText = "방금까지 살펴본<br>카카오톡의 화면이<br>이 버튼을 누르면<br>보이는 화면입니다."
            dialog.top = 425.0f/930.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "카카오톡으로<br>친구와 연락을<br>주고 받아 볼까요?"
            dialog.top = 300.0f/930.0f
            dialog.bottom = 300.0f/930.0f
            dialog.start = 50.0f/412.0f
            dialog.end = 50.0f/412.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            hands.add(
                EduHand(
                    id = "tap",
                    x = 100.0f/412.0f,
                    y = 200.0f/930.0f,

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })*/
    }
}
