package com.sungkyul.synergy.utils

import android.content.Context
import android.view.Gravity
import com.sungkyul.synergy.R

class EduCourses {
    companion object {
        fun defaultPhone(context: Context, width: Float, height: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, width)
            val height = AnimUtils.pxToDp(context, height)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.titleText = ""
            data0.dialog.contentText = "안녕하세요!"
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.duration = 0
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.contentText = "교육 진행을\n테스트 해 볼 거에요!"
            result.add(data1)

            val data2 = EduData()
            data2.dialog.titleText = "교육"
            data2.dialog.titleGravity = Gravity.CENTER
            data2.dialog.contentText = "제목과 내용 변경 테스트에요!"
            data2.dialog.contentGravity = Gravity.END
            data2.dialog.contentBolds = listOf(Pair(0, 2), Pair(4, 6))
            result.add(data2)

            val data3 = EduData()
            data3.dialog.contentText = "사이즈 변경 테스트에요!"
            data3.dialog.contentGravity = Gravity.START
            data3.dialog.contentBolds = listOf()
            data3.dialog.duration = 500
            data3.dialog.top = 320.0f
            data3.dialog.bottom = 320.0f
            data3.dialog.start = 60.0f
            data3.dialog.end = 60.0f
            result.add(data3)

            val data4 = EduData()
            data4.dialog.top = 300.0f
            data4.dialog.bottom = 300.0f
            data4.dialog.start = 0.0f
            data4.dialog.end = width/2
            result.add(data4)

            val data5 = EduData()
            data5.dialog.top = 0.0f
            data5.dialog.bottom = height/2
            data5.dialog.start = 50.0f
            data5.dialog.end = 50.0f
            result.add(data5)

            val data6 = EduData()
            data6.dialog.top = 300.0f
            data6.dialog.bottom = 300.0f
            data6.dialog.start = 50.0f
            data6.dialog.end = 50.0f
            result.add(data6)

            val data7 = EduData()
            data7.dialog.contentText = "박스와 화살표 테스트에요!"
            data7.cover.left = 50.0f
            data7.cover.top = 50.0f
            data7.cover.right = width-50.0f
            data7.cover.bottom = 100.0f
            data7.arrow.duration = 500
            data7.arrow.endTo = "box"
            data7.arrow.visibility = true
            result.add(data7)

            val data8 = EduData()
            data8.cover.duration = 500
            data8.cover.right = 100.0f
            result.add(data8)

            val data9 = EduData()
            data9.cover.left = 75.0f
            data9.cover.right = 125.0f
            result.add(data9)

            val data10 = EduData()
            data10.cover.left = 100.0f
            data10.cover.right = 150.0f
            data10.cover.hasStroke = true
            result.add(data10)

            val data11 = EduData()
            data11.dialog.top = 275.0f
            data11.dialog.bottom = 325.0f
            data11.cover.duration = 0
            data11.cover.top = 500.0f
            data11.cover.bottom = 550.0f
            data11.arrow.duration = 1000
            result.add(data11)

            val data12 = EduData()
            data12.cover.left = 150.0f
            data12.cover.right = 200.0f
            data12.arrow.duration = 500
            result.add(data12)

            val data13 = EduData()
            data13.cover.left = 0.0f
            data13.cover.top = 0.0f
            data13.cover.right = 0.0f
            data13.cover.bottom = 0.0f
            data13.cover.hasStroke = false
            data13.arrow.endTo = "dialog"
            result.add(data13)

            val data14 = EduData()
            data14.dialog.contentText = "다음에 나타날 손가락이\n가리키는 곳을 누르세요!"
            result.add(data14)

            val data15 = EduData()
            data15.dialog.visibility = false
            data15.cover.visibility = false
            data15.arrow.visibility = false
            data15.fingers.add(
                EduFinger(
                    "touch",
                    R.drawable.hand,
                    110.0f,
                    290.0f,
                    50.0f,
                    75.0f,
                    -90.0f,
                    HandAnims::touchGuideAnimatorSet
                )
            )
            data15.action.id = "click_key_button"
            data15.action.message = "1"
            result.add(data15)

            val data16 = EduData()
            data16.fingers.add(
                EduFinger(
                    "touch",
                    R.drawable.hand,
                    200.0f,
                    340.0f,
                    50.0f,
                    75.0f,
                    0.0f,
                    HandAnims::touchGuideAnimatorSet
                )
            )
            data16.action.id = "click_key_button"
            data16.action.message = "2"
            result.add(data16)

            val data17 = EduData()
            data17.dialog.contentText = "테스트 완료입니다!"
            data17.dialog.visibility = true
            data17.cover.visibility = true
            result.add(data17)

            return result
        }
    }
}
