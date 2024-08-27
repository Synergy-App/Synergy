package com.sungkyul.synergy.courses.default_app.camera

import android.os.Build
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultCameraFromGalleryCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "화면을 전환하는<br>버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = AdaptiveUtils.ratio(0.65f)
            dialog.bottom = AdaptiveUtils.ratio(0.15f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            dialog.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true

            cover.visibility=true
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxLeft=AdaptiveUtils.ratio(0.65f)
            cover.boxTop=AdaptiveUtils.ratio(0.82f)

            cover.boxRight=AdaptiveUtils.ratio(0.82f)
            cover.boxBottom=AdaptiveUtils.ratio(0.92f)
        })

        list.add(EduData().apply {
            dialog.contentText = "전면과 후면을<br>변경해주어 셀카를<br>찍을 수 있습니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = AdaptiveUtils.ratio(0.55f)
            dialog.bottom = AdaptiveUtils.ratio(0.15f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            dialog.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true

            cover.visibility=true
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxLeft=AdaptiveUtils.ratio(0.65f)
            cover.boxTop=AdaptiveUtils.ratio(0.82f)

            cover.boxRight=AdaptiveUtils.ratio(0.82f)
            cover.boxBottom=AdaptiveUtils.ratio(0.92f)
        })

        list.add(EduData().apply {
            dialog.contentText = "셀카를 찍고<br>앨범으로 이동해<br>사진을 확인해볼까요?"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = AdaptiveUtils.ratio(0.4f)
            dialog.bottom = AdaptiveUtils.ratio(0.4f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true

            cover.visibility=false
            cover.boxVisibility=false
            cover.boxBorderVisibility=false
        })

        list.add(EduData().apply {
            dialog.contentText="<p style='color:red'><b>잠깐!</b></p><br>현재 보이는 카메라는<br>" +
                    "사생활 문제로<br>" +
                    "작동되지 않아요!<br>" +
                    "<br>가상의 이미지일 뿐이랍니다.<br>참고해주세요!"
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor=R.color.black
            dialog.top=AdaptiveUtils.ratio(0.2f)
            dialog.bottom=AdaptiveUtils.ratio(0.2f)
        })

        list.add(EduData().apply {
            dialog.contentText="<p style='color:red'><b>잠깐!</b></p><br>현재 화면은 전면으로 <br>보이는 가상화면입니다.<br><br>본인의 모습을<br>" +
                    "찍는다고 생각하고<br>촬영을 진행해주세요."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor=R.color.black
            dialog.top=AdaptiveUtils.ratio(0.2f)
            dialog.bottom=AdaptiveUtils.ratio(0.2f)
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_camera_toggle_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(0.74f),
                    y = AdaptiveUtils.ratio(0.875f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_shooting_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(0.45f),
                    y = AdaptiveUtils.ratio(0.875f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_gallery_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(0.22f),
                    y = AdaptiveUtils.ratio(0.875f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
