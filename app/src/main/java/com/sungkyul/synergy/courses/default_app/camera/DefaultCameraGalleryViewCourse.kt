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

data class DefaultCameraGalleryViewCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "방금 찍은 사진입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.2f
            dialog.bottom = 0.6f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "앨범으로 돌아가는 버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            cover.boxLeft = AdaptiveUtils.ratio(0.0f)
            cover.boxTop = AdaptiveUtils.ratio(0.0f)
            cover.boxRight = AdaptiveUtils.ratio(0.2f)
            cover.boxBottom = AdaptiveUtils.ratio(0.1f)
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxBorderVisibility = true
            cover.boxVisibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_back_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(0.1f),
                    y = AdaptiveUtils.ratio(0.05f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })






        list.add(EduData().apply {
            dialog.contentText = "사진을 촬영하는<br>버튼입니다."
            cover.boxLeft = AdaptiveUtils.ratio(0.4f)
            cover.boxTop = AdaptiveUtils.ratio(0.82f)
            cover.boxRight = AdaptiveUtils.ratio(0.6f)
            cover.boxBottom = AdaptiveUtils.ratio(0.92f)
        })

        list.add(EduData().apply {
            dialog.contentText = "가장 최근에 찍힌 사진이<br>이 부분에 나타나고"
            cover.boxLeft = AdaptiveUtils.ratio(0.15f)
            cover.boxRight = AdaptiveUtils.ratio(0.4f)
        })

        list.add(EduData().apply {
            dialog.contentText = "터치하면 앨범으로<br>이동합니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "사진을 찍어보고<br>앨범을 확인해 볼까요?"
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor=R.color.white

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
            dialog.contentText="<p style='color:red'><b>잠깐!</b></p><br>현재 화면은 후면으로 <br>보이는 가상화면입니다.<br><br>앞에 보이는 풍경을<br>" +
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
            dialog.contentText = "사진이 찍혔습니다!"
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.contentColor=R.color.black
            dialog.top = AdaptiveUtils.ratio(0.5f)
            dialog.bottom = AdaptiveUtils.ratio(0.3f)
            dialog.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "방금 찍은 사진을<br>확인해 볼까요?"
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor=R.color.white
            dialog.top = AdaptiveUtils.ratio(0.4f)
            dialog.bottom = AdaptiveUtils.ratio(0.4f)
            cover.visibility = true
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
