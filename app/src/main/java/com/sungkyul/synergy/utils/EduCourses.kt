package com.sungkyul.synergy.utils

import android.content.Context
import android.view.Gravity
import com.sungkyul.synergy.R

class EduCourses {
    companion object {
        /*
        ## 교육 코스를 추가해보자!
        ```
        fun nameCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            result.add(data0)

            return result
        }
        ```
        */

        fun defaultPhoneTestCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
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
            data7.cover.boxVisibility = true
            data7.cover.boxLeft = 50.0f
            data7.cover.boxTop = 50.0f
            data7.cover.boxRight = width-50.0f
            data7.cover.boxBottom = 100.0f
            data7.arrow.duration = 500
            data7.arrow.endTo = "box"
            data7.arrow.visibility = true
            result.add(data7)

            val data8 = EduData()
            data8.cover.duration = 500
            data8.cover.boxRight = 100.0f
            result.add(data8)

            val data9 = EduData()
            data9.cover.boxLeft = 75.0f
            data9.cover.boxRight = 125.0f
            result.add(data9)

            val data10 = EduData()
            data10.cover.boxLeft = 100.0f
            data10.cover.boxRight = 150.0f
            data10.cover.boxStrokeVisibility = true
            result.add(data10)

            val data11 = EduData()
            data11.dialog.top = 275.0f
            data11.dialog.bottom = 325.0f
            data11.cover.duration = 0
            data11.cover.boxTop = 500.0f
            data11.cover.boxBottom = 550.0f
            data11.arrow.duration = 1000
            result.add(data11)

            val data12 = EduData()
            data12.cover.boxLeft = 150.0f
            data12.cover.boxRight = 200.0f
            data12.arrow.duration = 500
            result.add(data12)

            val data13 = EduData()
            data13.cover.boxVisibility = false
            data13.cover.boxStrokeVisibility = false
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
                    HandGestures::createTapGesture
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
                    HandGestures::createTapGesture
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

        fun kakaoCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.contentText = "카카오톡의\n메인 화면입니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.duration = 750
            data1.arrow.duration = 750
            data1.dialog.top = 200.0f
            data1.dialog.bottom = 400.0f
            data1.cover.boxVisibility = true
            data1.cover.boxStrokeVisibility = true
            data1.cover.boxLeft = 225.0f
            data1.cover.boxTop = 15.0f
            data1.cover.boxRight = 275.0f
            data1.cover.boxBottom = 65.0f
            data1.arrow.endTo = "box"
            data1.arrow.visibility = true
            data1.dialog.contentText = "이 버튼은 빠르게\n친구를 찾을 수 있는\n버튼이에요."
            result.add(data1)

            val data2 = EduData()
            data2.dialog.contentText = "친구를 빠르게 찾고\n싶을 때 이 버튼을 눌러\n찾고 싶은 친구의 이름을\n입력하면 돼요."
            result.add(data2)

            val data3 = EduData()
            data3.cover.boxLeft = 10.0f
            data3.cover.boxTop = 75.0f
            data3.cover.boxRight = width-10.0f
            data3.cover.boxBottom = 135.0f
            data3.dialog.contentText = "내 프로필입니다."
            result.add(data3)

            val data4 = EduData()
            data4.dialog.titleText = "프로필이란?"
            data4.dialog.contentText = "카카오톡을 사용할 때\n다른 사람들에게\n여러분을 알리는데\n도움을 줄 수 있는\n간단한 자기소개입니다."
            data4.dialog.bottom = 300.0f
            result.add(data4)

            val data5 = EduData()
            data5.dialog.titleText = ""
            data5.dialog.contentText = "친구들의 목록을 볼 수 있어요."
            data5.dialog.top = 25.0f
            data5.dialog.bottom = 625.0f
            data5.cover.boxTop = 175.0f
            data5.cover.boxBottom = 700.0f
            data5.arrow.endTo = "dialog"
            result.add(data5)

            val data6 = EduData()
            data6.dialog.contentText = "친구를 찾을 수 있는\n버튼입니다."
            data6.dialog.duration = 750
            data6.arrow.duration = 750
            data6.dialog.top = 450.0f
            data6.dialog.bottom = 150.0f
            data6.dialog.start = 25.0f
            data6.dialog.end = 75.0f
            data6.cover.boxLeft = 25.0f
            data6.cover.boxTop = height-50.0f
            data6.cover.boxRight = 75.0f
            data6.cover.boxBottom = height-0.0f
            data6.arrow.endTo = "box"
            result.add(data6)

            val data7 = EduData()
            data7.dialog.duration = 750
            data7.arrow.duration = 750
            data7.dialog.contentText = "방금까지 살펴본\n카카오톡의 화면이\n이 버튼을 누르면\n보이는 화면입니다."
            data7.dialog.top = 425.0f
            result.add(data7)

            val data8 = EduData()
            data8.dialog.contentText = "카카오톡으로\n친구와 연락을\n주고 받아 볼까요?"
            data8.dialog.top = 300.0f
            data8.dialog.bottom = 300.0f
            data8.dialog.start = 50.0f
            data8.dialog.end = 50.0f
            data8.cover.boxVisibility = false
            data8.cover.boxStrokeVisibility = false
            data8.arrow.endTo = "dialog"
            result.add(data8)

            val data9 = EduData()
            data9.dialog.visibility = false
            data9.cover.visibility = false
            data9.arrow.visibility = false
            data9.fingers.add(EduFinger(
                    id = "touch",
                    source = R.drawable.hand,
                    x = 100.0f,
                    y = 200.0f,
                    width = 50.0f,
                    height = 75.0f,
                    gesture = HandGestures::createTapGesture
                )
            )
            result.add(data9)

            return result
        }

        fun kakaoProfileCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.contentText = "친구의 프로필입니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.contentText = "'1:1 채팅'은 친구와\n대화할 수 있는 기능입니다."
            data1.dialog.duration = 750
            data1.dialog.top = 400.0f
            data1.dialog.bottom = 200.0f
            data1.cover.boxVisibility = true
            data1.cover.boxStrokeVisibility = true
            data1.cover.boxLeft = 25.0f
            data1.cover.boxTop = height-95.0f
            data1.cover.boxRight = 110.0f
            data1.cover.boxBottom = height-10.0f
            data1.arrow.duration = 750
            data1.arrow.endTo = "box"
            data1.arrow.visibility = true
            result.add(data1)

            val data2 = EduData()
            data2.dialog.contentText = "'보이스톡'은 친구의\n전화 번호가 없어도\n전화를 할 수 있습니다."
            data2.cover.duration = 750
            data2.cover.boxLeft = 165.0f
            data2.cover.boxRight = 250.0f
            result.add(data2)

            val data3 = EduData()
            data3.dialog.contentText = "와이파이가 없는\n곳에서는 데이터가\n나갈 수 있으니\n주의해주세요!"
            result.add(data3)

            val data4 = EduData()
            data4.dialog.contentText = "페이스톡은 영상통화입니다."
            data4.cover.boxLeft = width-110.0f
            data4.cover.boxRight = width-25.0f
            result.add(data4)

            val data5 = EduData()
            data5.dialog.contentText = "보이스톡과 같이\n와이파이가 없으면\n데이터가 나가니\n주의해주세요."
            result.add(data5)

            val data6 = EduData()
            data6.dialog.contentText = "그럼 1:1 대화를\n시작해볼까요?"
            data6.dialog.top = 300.0f
            data6.dialog.bottom = 300.0f
            data6.dialog.start = 50.0f
            data6.dialog.end = 50.0f
            data6.cover.duration = 0
            data6.cover.boxVisibility = false
            data6.cover.boxStrokeVisibility = false
            data6.arrow.endTo = "dialog"
            result.add(data6)

            val data7 = EduData()
            data7.dialog.visibility = false
            data7.cover.visibility = false
            data7.arrow.visibility = false
            data7.action.id = "click_chat11_button"
            data7.fingers.add(EduFinger(
                id = "touch",
                source = R.drawable.hand,
                x = 85.0f,
                y = 675.0f,
                width = 50.0f,
                height = 75.0f,
                rotation = -90.0f,
                gesture = HandGestures::createTapGesture
            ))
            result.add(data7)

            return result
        }

        fun kakaoChatCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            result.add(data0)

            return result
        }
    }
}
