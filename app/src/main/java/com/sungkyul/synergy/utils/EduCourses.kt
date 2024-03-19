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

            result.add(EduData().apply {
            })

            return result
        }
        ```
        */

        // for ScreenLayoutActivity
        fun screenLayoutCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "이 부분은\n<b>홈 화면</b>입니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.boxLeft = 20.0f
                cover.boxTop = 50.0f
                cover.boxRight = width-20.0f
                cover.boxBottom = height-50.0f
                cover.visibility = true
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
            })

            result.add(EduData().apply {
                dialog.titleText = "홈 화면"
                dialog.contentText = "주로 <b>배경화면</b>이라고\n부릅니다."
                dialog.contentGravity = Gravity.START
                dialog.duration = 750
                dialog.top = 275.0f
                dialog.bottom = 275.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "자주 사용하는 앱이\n화면에 배치되어\n있습니다."
                dialog.top = 250.0f
                dialog.bottom = 250.0f
            })

            result.add(EduData().apply {
                dialog.titleText = ""
                dialog.contentText = "이 부분은\n<b>상단바(상태 표시줄)</b>\n입니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 100.0f
                dialog.bottom = 490.0f

                cover.boxLeft = 0.0f
                cover.boxTop = 5.0f
                cover.boxRight = width
                cover.boxBottom = 55.0f

                arrow.duration = 750
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "주로 스마트폰의\n상태를 보여줍니다."
            })

            result.add(EduData().apply {
                dialog.contentText = "좌측 상단에는\n<b>현재 시간</b>이\n나타납니다."

                cover.duration = 750
                cover.boxRight = 100.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "우측 상단은\n<b>스마트폰의 현재\n상태</b>를 나타냅니다."

                cover.boxLeft = width-150.0f
                cover.boxRight = width
            })

            // TODO(상단바를 내릴 수 있는 기능 추가하기!)
            // 상단바(상태 표시줄)을\n내려주세요.
            // 대충 손가락이 위에서 아래로 내려오는 느낌

            result.add(EduData().apply {
                dialog.contentText = "이 부분은\n<b>하단바(내비게이션바)</b>\n입니다."
                dialog.duration = 1000
                dialog.top = 500.0f
                dialog.bottom = 100.0f

                cover.duration = 0
                cover.boxLeft = 0.0f
                cover.boxTop = height-55.0f
                cover.boxRight = width
                cover.boxBottom = height-5.0f

                arrow.duration = 1000
            })

            result.add(EduData().apply {
                dialog.contentText = "이 버튼은\n<b>최근 실행 앱</b>을\n볼 수 있습니다."
                dialog.duration = 750

                cover.duration = 750
                cover.boxLeft = 50.0f
                cover.boxRight = 110.0f

                arrow.duration = 750
            })

            result.add(EduData().apply {
                dialog.contentText = "이 버튼은\n<b>홈 화면</b>으로\n돌아갈 수 있습니다."

                cover.boxLeft = 175.0f
                cover.boxRight = 235.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "이 버튼은\n<b>뒤로(이전 화면)</b>\n돌아갈 수 있습니다."

                cover.boxLeft = width-110.0f
                cover.boxRight = width-50.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "<b>상단바(상태 표시줄)</b>를\n한 번 내려볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f

                cover.boxVisibility = false
                cover.boxStrokeVisibility = false

                arrow.endTo = EduScreen.DIALOG
            })

            return result
        }

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
            data2.dialog.contentText = "<b>제목</b>과 <b>내용</b> 변경 테스트에요!"
            data2.dialog.contentGravity = Gravity.END
            result.add(data2)

            val data3 = EduData()
            data3.dialog.contentText = "사이즈 변경 테스트에요!"
            data3.dialog.contentGravity = Gravity.START
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
            data7.arrow.endTo = EduScreen.BOX
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
            data13.arrow.endTo = EduScreen.DIALOG
            result.add(data13)

            val data14 = EduData()
            data14.dialog.contentText = "다음에 나타날 손가락이\n가리키는 곳을 누르세요!"
            result.add(data14)

            val data15 = EduData()
            data15.dialog.visibility = false
            data15.cover.visibility = false
            data15.arrow.visibility = false
            data15.hands.add(
                EduHand(
                    "touch",
                    R.drawable.hand,
                    110.0f,
                    290.0f,
                    50.0f,
                    75.0f,
                    -90.0f,
                    HandGestures::tapGesture
                )
            )
            data15.action.id = "click_key_button"
            data15.action.message = "1"
            result.add(data15)

            val data16 = EduData()
            data16.hands.add(
                EduHand(
                    "touch",
                    R.drawable.hand,
                    200.0f,
                    340.0f,
                    50.0f,
                    75.0f,
                    0.0f,
                    HandGestures::tapGesture
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

        fun defaultPhoneCourse1(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            val data0 = EduData()
            data0.dialog.contentText = "해당 부분은 다른\n사람의 전화번호를\n입력하는 버튼이에요."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 100.0f
            data0.dialog.bottom = 500.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.cover.boxLeft = 50.0f
            data0.cover.boxTop = 300.0f
            data0.cover.boxRight = width-50.0f
            data0.cover.boxBottom = 600.0f
            data0.cover.boxVisibility = true
            data0.cover.boxStrokeVisibility = true
            data0.arrow.endTo = EduScreen.DIALOG
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            // 1
            val data1 = EduData()
            data1.dialog.duration = 750
            data1.cover.duration = 750
            data1.arrow.duration = 750
            data1.dialog.top = 300.0f
            data1.dialog.bottom = 300.0f
            data1.cover.boxLeft = 150.0f
            data1.cover.boxTop = 620.0f
            data1.cover.boxRight = width-150.0f
            data1.cover.boxBottom = 720.0f
            data1.arrow.endTo = EduScreen.BOX
            data1.arrow.visibility = true
            data1.dialog.contentText = "전화를 거는 버튼입니다.\n이 버튼을 누르면\n상대방과 통화를\n할 수 있어요."
            result.add(data1)

            // 2
            val data2 = EduData()
            data2.dialog.contentText = "통화를 걸어볼까요?"
            data2.cover.boxVisibility = false
            data2.cover.boxStrokeVisibility = false
            data2.arrow.endTo = EduScreen.DIALOG
            result.add(data2)

            // 3
            val data3 = EduData()
            data3.dialog.visibility = false
            data3.cover.visibility = false
            data3.arrow.visibility = false
            data3.action.id = "click_key_button"
            data3.action.message = "0"
            data3.hands.add(EduHand(
                id = "tap",
                x = 190.0f,
                y = 580.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data3)

            val phoneNumber = "1024683579"
            val keyPosition = hashMapOf(
                '1' to Pair(80.0f, 340.0f),
                '2' to Pair(190.0f, 340.0f),
                '3' to Pair(300.0f, 340.0f),
                '4' to Pair(80.0f, 420.0f),
                '5' to Pair(190.0f, 420.0f),
                '6' to Pair(300.0f, 420.0f),
                '7' to Pair(80.0f, 500.0f),
                '8' to Pair(190.0f, 500.0f),
                '9' to Pair(300.0f, 500.0f),
                '*' to Pair(80.0f, 580.0f),
                '0' to Pair(190.0f, 580.0f),
                '#' to Pair(300.0f, 580.0f)
            )
            for(i in phoneNumber) {
                val dataK = EduData()
                dataK.action.id = "click_key_button"
                dataK.action.message = i.toString()
                dataK.hands.add(EduHand(
                    id = "tap",
                    x = keyPosition[i]!!.first,
                    y = keyPosition[i]!!.second,
                    gesture = HandGestures::tapGesture
                ))
                result.add(dataK)
            }

            // 4
            val data4 = EduData()
            data4.action.id = "click_call_button"
            data4.hands.add(EduHand(
                id = "tap",
                x = 200.0f,
                y = 670.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data4)

            return result
        }

        fun defaultPhoneCourse2(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            val data0 = EduData()
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 500.0f
            data0.dialog.bottom = 100.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.cover.boxLeft = 150.0f
            data0.cover.boxTop = 730.0f
            data0.cover.boxRight = width-150.0f
            data0.cover.boxBottom = height
            data0.arrow.endTo = EduScreen.BOX
            data0.dialog.visibility = true
            data0.cover.visibility = true
            data0.cover.boxVisibility = true
            data0.cover.boxStrokeVisibility = true
            data0.arrow.visibility = true
            data0.dialog.contentText = "이 부분을 통해\n방금 전화를 건\n통화 목록을\n확인할 수 있어요."
            result.add(data0)

            // 1
            val data1 = EduData()
            data1.dialog.contentText = "최근기록을 눌러주세요."
            result.add(data1)

            // 2
            val data2 = EduData()
            data2.dialog.visibility = false
            data2.cover.visibility = false
            data2.cover.boxVisibility = false
            data2.cover.boxStrokeVisibility = false
            data2.arrow.visibility = false
            data2.action.id = "click_recent_history_button"
            data2.hands.add(EduHand(
                id = "tap",
                x = 250.0f,
                y = 670.0f,
                rotation = 225.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data2)

            // 3
            val data3 = EduData()
            data3.dialog.top = 300.0f
            data3.dialog.bottom = 300.0f
            data3.dialog.start = 50.0f
            data3.dialog.end = 50.0f
            data3.dialog.visibility = true
            data3.cover.visibility = true
            data3.arrow.endTo = EduScreen.DIALOG
            data3.dialog.contentText = "최근기록을 보면\n전화 성공/실패 여부나\n영상 통화 여부 등을\n확인할 수 있어요."
            result.add(data3)

            // 4
            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.top = 500.0f
                dialog.bottom = 100.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxLeft = width-120.0f
                cover.boxTop = 730.0f
                cover.boxRight = width-20.0f
                cover.boxBottom = height
                arrow.endTo = EduScreen.BOX
                dialog.visibility = true
                cover.visibility = true
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.visibility = true
                dialog.contentText = "이 부분은\n전화번호를 저장해서\n편리하게 전화를\n걸 수 있어요."
            })

            return result
        }

        fun defaultPhoneCallCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "지금 현재 상대방에게\n통화하고 있는 중이에요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.contentText = "근데 상대방이 전화를\n안 받는 것 같아요.\n통화를 끊어볼까요?"
            })

            // 2
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                action.id = "click_hang_up_button"
                hands.add(EduHand(
                    id = "tap",
                    x = 190.0f,
                    y = 700.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            result.add(EduData())

            return result
        }

        // for CameraActivity
        fun cameraCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "위 메뉴는 카메라의\n플래시, 타이머 등을\n설정할 수 있어요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 200.0f
                dialog.bottom = 400.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxLeft = 20.0f
                cover.boxTop = 5.0f
                cover.boxRight = width-20.0f
                cover.boxBottom = 45.0f
                arrow.endTo = EduScreen.BOX
                dialog.visibility = true
                cover.visibility = true
                cover.boxStrokeVisibility = true
                cover.boxVisibility = true
                arrow.visibility = true
            })

            // 0
            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "아래 버튼은 사진,\n동영상 등 자신이\n원하는 촬영을\n선택할 수 있어요."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                cover.boxLeft = 30.0f
                cover.boxTop = 620.0f
                cover.boxRight = width-30.0f
                cover.boxBottom = 650.0f
            })

            // 1
            result.add(EduData().apply {
                cover.duration = 750
                dialog.contentText = "촬영할 수 있는\n버튼이에요.\n버튼을 클릭하면\n사진 저장이 돼요."
                dialog.top = 400.0f
                dialog.bottom = 200.0f
                cover.boxLeft = 160.0f
                cover.boxTop = height-105.0f
                cover.boxRight = width-160.0f
                cover.boxBottom = height-15.0f
            })

            // 2
            result.add(EduData().apply {
                dialog.contentText = "찍은 사진을\n확인할 수 있는\n앨범이에요."
                cover.boxLeft = 55.0f
                cover.boxTop = height-105.0f
                cover.boxRight = 135.0f
                cover.boxBottom = height-15.0f
            })

            // 3
            result.add(EduData().apply {
                dialog.contentText = "사진을 찍으면\n이 부분에 나타나요."
            })

            // 4
            result.add(EduData().apply {
                dialog.contentText = "화면을 전환하는\n부분이에요."
                cover.boxLeft = width-135.0f
                cover.boxTop = height-105.0f
                cover.boxRight = width-55.0f
                cover.boxBottom = height-15.0f
            })

            // 5
            result.add(EduData().apply {
                dialog.contentText = "버튼을 클릭하면\n나를 찍을 수도 있고,\n상대방을 찍을 수도\n있어요."
                dialog.top = 350.0f
            })

            // 6
            result.add(EduData().apply {
                dialog.contentText = "사진을 촬영해볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxStrokeVisibility = false
                cover.boxVisibility = false
                arrow.endTo = EduScreen.DIALOG
            })

            // 7
            result.add(EduData().apply {
                dialog.contentText = "손가락을 따라\n클릭해주세요."
            })

            // 8
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_shooting_button"
                hands.add(EduHand(
                    id = "tap",
                    x = 220.0f,
                    y = height-100.0f,
                    rotation = -90.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            // 9
            result.add(EduData().apply {
                dialog.duration = 0
                cover.duration = 0
                arrow.duration = 0
                dialog.contentText = "방금 찍은 사진은\n여기에서 확인할 수\n있어요."
                dialog.top = 400.0f
                dialog.bottom = 200.0f
                cover.boxLeft = 55.0f
                cover.boxTop = height-105.0f
                cover.boxRight = 135.0f
                cover.boxBottom = height-15.0f
                arrow.endTo = EduScreen.BOX
                dialog.visibility = true
                cover.visibility = true
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.visibility = true
            })

            // 10
            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "클릭해주세요."
                dialog.top = 450.0f
            })

            // 11
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.visibility = false
                action.id = "click_gallery_view_button"
                hands.add(EduHand(
                    id = "tap",
                    x = 120.0f,
                    y = height-100.0f,
                    rotation = -90.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            return result
        }

        // for DefaultMessageActivity
        fun defaultMessageCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "여기는 채팅방으로\n상대방과 메시지를\n주고받을 수 있어요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "여기서 문자 메시지를\n입력할 수 있어요."
                dialog.top = 450.0f
                dialog.bottom = 150.0f
                cover.boxLeft = 90.0f
                cover.boxTop = height-60.0f
                cover.boxRight = width-90.0f
                cover.boxBottom = height
                arrow.endTo = EduScreen.BOX
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.visibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 750
                cover.duration = 750
                arrow.duration = 750
                dialog.contentText = "이 버튼을 누르면\n메시지가 전송돼요."
                cover.boxLeft = width-55.0f
                cover.boxRight = width
            })

            result.add(EduData().apply {
                dialog.contentText = "시너지에게\n메시지를 보내볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                arrow.endTo = EduScreen.DIALOG
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_message_edit_text"
                hands.add(EduHand(
                    id = "tap",
                    x = width/2,
                    y = height-110.0f,
                    rotation = 180.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            result.add(EduData().apply {
                action.id = "click_send_button"
            })

            result.add(EduData().apply {
                dialog.duration = 0
                cover.duration = 0
                dialog.contentText = "문자 메시지를\n성공적으로 보냈습니다!"
                dialog.top = 250.0f
                dialog.bottom = 350.0f
                cover.boxLeft = 0.0f
                cover.boxTop = height
                cover.boxRight = width
                cover.boxBottom = height
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 750
                cover.duration = 750
                dialog.contentText = "자신이 보낸 메시지는\n아래에 보이는 것처럼\n확인할 수 있어요."
                dialog.top = 150.0f
                dialog.bottom = 450.0f
                cover.boxTop = 360.0f
                cover.boxBottom = height-60.0f
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
            })

            return result
        }

        // for DefaultMessageChattingActivity
        fun defaultMessageChattingCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "여기는 그동안\n주고받은 메시지를\n확인할 수 있어요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 50.0f
                dialog.bottom = 550.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxLeft = 0.0f
                cover.boxTop = 280.0f
                cover.boxRight = width-0.0f
                cover.boxBottom = height-60.0f
                dialog.visibility = true
                cover.visibility = true
                cover.boxStrokeVisibility = true
                cover.boxVisibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 1000
                cover.duration = 750
                arrow.duration = 1000
                dialog.contentText = "이 버튼은 새로운\n메시지를 작성할 수 있어요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 350.0f
                dialog.bottom = 250.0f
                cover.boxLeft = width-80.0f
                cover.boxTop = 630.0f
                cover.boxRight = width-10.0f
                cover.boxBottom = height-70.0f
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "메시지를 작성해볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                arrow.endTo = EduScreen.DIALOG
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
            })

            result.add(EduData().apply {
                cover.duration = 0
                dialog.contentText = "시너지를 클릭해주세요."
                dialog.top = 250.0f
                dialog.bottom = 350.0f
                cover.boxLeft = 0.0f
                cover.boxTop = 135.0f
                cover.boxRight = width-0.0f
                cover.boxBottom = 200.0f
                arrow.endTo = EduScreen.BOX
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.visibility = false
                action.id = "click_message_chatting_item"
                action.message = "시너지"
                hands.add(EduHand(
                    id = "tap",
                    x = width/2,
                    y = 170.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            return result
        }

        fun kakaoCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            val data0 = EduData()
            data0.dialog.contentText = "<span style=\"color:#E6C60D\"><b>카카오톡</b></span>의\n메인 화면입니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            // 1
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
            data1.arrow.endTo = EduScreen.BOX
            data1.arrow.visibility = true
            data1.dialog.contentText = "이 버튼은 빠르게\n친구를 찾을 수 있는\n버튼이에요."
            result.add(data1)

            // 2
            val data2 = EduData()
            data2.dialog.contentText = "친구를 빠르게 찾고\n싶을 때 이 버튼을 눌러\n찾고 싶은 친구의 이름을\n입력하면 돼요."
            result.add(data2)

            // 3
            val data3 = EduData()
            data3.cover.boxLeft = 10.0f
            data3.cover.boxTop = 75.0f
            data3.cover.boxRight = width-10.0f
            data3.cover.boxBottom = 135.0f
            data3.dialog.contentText = "내 프로필입니다."
            result.add(data3)

            // 4
            val data4 = EduData()
            data4.dialog.titleText = "프로필이란?"
            data4.dialog.contentText = "카카오톡을 사용할 때\n다른 사람들에게\n여러분을 알리는데\n도움을 줄 수 있는\n간단한 자기소개입니다."
            data4.dialog.bottom = 300.0f
            result.add(data4)

            // 5
            val data5 = EduData()
            data5.dialog.titleText = ""
            data5.dialog.contentText = "친구들의 목록을 볼 수 있어요."
            data5.dialog.top = 25.0f
            data5.dialog.bottom = 625.0f
            data5.cover.boxTop = 175.0f
            data5.cover.boxBottom = 700.0f
            data5.arrow.endTo = EduScreen.DIALOG
            result.add(data5)

            // 6
            val data6 = EduData()
            data6.dialog.contentText = "친구를 찾을 수 있는\n버튼입니다."
            data6.dialog.duration = 750
            data6.arrow.duration = 750
            data6.dialog.top = 450.0f
            data6.dialog.bottom = 150.0f
            data6.dialog.start = 25.0f
            data6.dialog.end = 75.0f
            data6.cover.boxLeft = 25.0f
            data6.cover.boxTop = height-70.0f
            data6.cover.boxRight = 80.0f
            data6.cover.boxBottom = height-0.0f
            data6.arrow.endTo = EduScreen.BOX
            result.add(data6)

            // 7
            val data7 = EduData()
            data7.dialog.duration = 750
            data7.arrow.duration = 750
            data7.dialog.contentText = "방금까지 살펴본\n카카오톡의 화면이\n이 버튼을 누르면\n보이는 화면입니다."
            data7.dialog.top = 425.0f
            result.add(data7)

            // 8
            val data8 = EduData()
            data8.dialog.contentText = "카카오톡으로\n친구와 연락을\n주고 받아 볼까요?"
            data8.dialog.top = 300.0f
            data8.dialog.bottom = 300.0f
            data8.dialog.start = 50.0f
            data8.dialog.end = 50.0f
            data8.cover.boxVisibility = false
            data8.cover.boxStrokeVisibility = false
            data8.arrow.endTo = EduScreen.DIALOG
            result.add(data8)

            // 9
            val data9 = EduData()
            data9.dialog.visibility = false
            data9.cover.visibility = false
            data9.arrow.visibility = false
            data9.hands.add(EduHand(
                    id = "tap",
                    x = 100.0f,
                    y = 200.0f,
                    gesture = HandGestures::tapGesture
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
            data1.arrow.endTo = EduScreen.BOX
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
            data6.arrow.endTo = EduScreen.DIALOG
            result.add(data6)

            val data7 = EduData()
            data7.dialog.visibility = false
            data7.cover.visibility = false
            data7.arrow.visibility = false
            data7.action.id = "click_chat11_button"
            data7.hands.add(EduHand(
                id = "tap",
                source = R.drawable.hand,
                x = 85.0f,
                y = 675.0f,
                width = 50.0f,
                height = 75.0f,
                rotation = -90.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data7)

            return result
        }

        fun kakaoChatCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            val data0 = EduData()
            data0.dialog.contentText = "이 공간은\n친구와 채팅을\n할 수 있습니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            // 1
            /*val data1 = EduData()
            data1.dialog.visibility = false
            data1.cover.visibility = false
            data1.arrow.visibility = false
            data1.action.id = "click_message_edit_text"
            data1.hands.add(EduHand(
                id = "tap",
                source = R.drawable.hand,
                x = 200.0f,
                y = 675.0f,
                rotation = 180.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data1)*/

            // 2
            val data2 = EduData()
            data2.dialog.contentText = "메시지를 보내볼까요?"
            data2.dialog.visibility = true
            data2.cover.visibility = true
            result.add(data2)

            // temp
            val data1 = EduData()
            data1.dialog.visibility = false
            data1.cover.visibility = false
            data1.arrow.visibility = false
            data1.action.id = "click_message_edit_text"
            data1.hands.add(EduHand(
                id = "tap",
                source = R.drawable.hand,
                x = 200.0f,
                y = 675.0f,
                rotation = 180.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data1)

            return result
        }

        // activity_setting2_main
        fun settingsCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.contentText = "화면 밝기와\n글자 크기에 대해서\n배워보겠습니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.contentText = "맨 아래까지 스크롤해주세요."
            result.add(data1)

            val data2 = EduData()
            data2.dialog.visibility = false
            data2.cover.visibility = false
            data2.action.id = "scroll_to_bottom"
            data2.hands.add(EduHand(
                id = "drag",
                x = 200.0f,
                y = 650.0f,
                gesture = HandGestures::settingsScrollDownGesture
            ))
            result.add(data2)

            val data3 = EduData()
            data3.dialog.contentText = "<b>디스플레이</b>는\n스마트폰의 다양한 설정을 할 수\n있습니다."
            data3.dialog.top = 450.0f
            data3.dialog.bottom = 150.0f
            data3.cover.boxLeft = 20.0f
            data3.cover.boxTop = 320.0f
            data3.cover.boxRight = width-20.0f
            data3.cover.boxBottom = 390.0f
            data3.dialog.visibility = true
            data3.cover.visibility = true
            data3.cover.boxVisibility = true
            data3.cover.boxStrokeVisibility = true
            result.add(data3)

            val data4 = EduData()
            data4.dialog.contentText = "디스플레이를 클릭해주세요."
            result.add(data4)

            val data5 = EduData()
            data5.dialog.visibility = false
            data5.cover.visibility = false
            data5.cover.boxVisibility = false
            data5.cover.boxStrokeVisibility = false
            data5.action.id = "tap_display_item"
            data5.hands.add(EduHand(
                id = "tap",
                x = width/2,
                y = 360.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data5)

            return result
        }

        fun settingsDisplayCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.contentText = "먼저 스마트폰\n밝기 조절을\n배워보겠습니다."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.contentText = "손가락을 따라\n움직여주세요."
            result.add(data1)

            val data2 = EduData()
            data2.dialog.visibility = false
            data2.cover.visibility = false
            data2.action.id = "change_light_bar"
            data2.action.message = "100"
            data2.hands.add(EduHand(
                id = "drag",
                x = 220.0f,
                y = 510.0f,
                gesture = HandGestures::displayLightDragGesture
            ))
            result.add(data2)

            val data3 = EduData()
            data3.dialog.contentText = "이번엔 글자 크기를\n조절해보겠습니다."
            data3.dialog.visibility = true
            data3.cover.visibility = true
            result.add(data3)

            val data4 = EduData()
            data4.dialog.contentText = "맨 아래까지 스크롤해주세요."
            result.add(data4)

            val data5 = EduData()
            data5.dialog.visibility = false
            data5.cover.visibility = false
            data5.action.id = "scroll_to_bottom"
            data5.hands.add(EduHand(
                id = "drag",
                source = R.drawable.hand,
                x = 200.0f,
                y = 650.0f,
                width = 50.0f,
                height = 75.0f,
                gesture = HandGestures::displayScrollGesture
            ))
            result.add(data5)

            val data6 = EduData()
            data6.action.id = "tap_font_item"
            data6.hands.add(EduHand(
                id = "tap",
                x = 250.0f,
                y = 475.0f,
                gesture = HandGestures::tapGesture
            ))
            result.add(data6)

            return result
        }

        fun settingsFontCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            val data0 = EduData()
            data0.dialog.contentText = "손가락을 따라\n움직여주세요."
            data0.dialog.contentGravity = Gravity.CENTER
            data0.dialog.top = 300.0f
            data0.dialog.bottom = 300.0f
            data0.dialog.start = 50.0f
            data0.dialog.end = 50.0f
            data0.dialog.visibility = true
            data0.cover.visibility = true
            result.add(data0)

            val data1 = EduData()
            data1.dialog.visibility = false
            data1.cover.visibility = false
            data1.action.id = "change_text_size_bar"
            data1.action.message = "7"
            data1.hands.add(EduHand(
                id = "drag",
                x = 40.0f,
                y = 660.0f,
                rotation = 180.0f,
                gesture = HandGestures::textSizeDragGesture
            ))
            result.add(data1)

            val data2 = EduData()
            data2.dialog.visibility = true
            data2.cover.visibility = true
            data2.dialog.contentText = "이것으로\n환경 설정 교육을\n마치겠습니다.\n수고하셨습니다!"
            result.add(data2)

            return result
        }

        // for GoogleMainActivity
        fun googleMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "로그인 버튼을\n클릭해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                hands.add(EduHand(
                    id = "tap",
                    x = width-60.0f,
                    y = 50.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            return result
        }

        // for GoogleDefaultInfoActivity
        fun googleDefaultInfoCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "기본 정보를\n입력해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleGetCodeActivity
        fun googleGetCodeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "전화번호를\n입력해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleLoginActivity
        fun googleLoginCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "<b>계정 만들기</b>를 \n클릭해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                hands.add(EduHand(
                    id = "tap",
                    x = 40.0f,
                    y = 520.0f,
                    gesture = HandGestures::tapGesture
                ))
            })

            return result
        }

        // for GoogleMailActivity
        fun googleMailCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "원하는 아이디를\n만들어주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleMailAddActivity
        fun googleMailAddCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
            })

            return result
        }

        // for GoogleMakeActivity
        fun googleMakeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "자신의 이름을\n작성해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GooglePasswordActivity
        fun googlePasswordCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "비밀번호를\n생성해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GooglePutCodeActivity
        fun googlePutCodeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = AnimUtils.pxToDp(context, widthDp)
            val height = AnimUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            // 0
            result.add(EduData().apply {
                dialog.contentText = "보안문자가 올 때까지\n기다려볼까요?"
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            // 1
            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }
    }
}
