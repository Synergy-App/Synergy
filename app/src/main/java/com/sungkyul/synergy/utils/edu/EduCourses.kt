package com.sungkyul.synergy.utils.edu

import android.content.Context
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures

class EduCourses {
    companion object {
        /*
        ## 교육 코스를 추가해보자!
        ```
        fun nameCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
            })

            return result
        }
        ```
        */

        // for ScreenLayoutActivity
        fun screenLayoutCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "이 부분은<br><b>홈 화면</b>입니다."
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
                dialog.contentText = "주로 <b>배경화면</b>이라고<br>부릅니다."
                dialog.contentGravity = Gravity.START
                dialog.duration = 750
                dialog.top = 275.0f
                dialog.bottom = 275.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "자주 사용하는 앱이<br>화면에 배치되어<br>있습니다."
                dialog.top = 250.0f
                dialog.bottom = 250.0f
            })

            result.add(EduData().apply {
                dialog.titleText = ""
                dialog.contentText = "이 부분은<br><b>상단바(상태 표시줄)</b><br>입니다."
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
                dialog.contentText = "주로 스마트폰의<br>상태를 보여줍니다."
            })

            result.add(EduData().apply {
                dialog.contentText = "좌측 상단에는<br><b>현재 시간</b>이<br>나타납니다."

                cover.duration = 750
                cover.boxRight = 100.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "우측 상단은<br><b>스마트폰의 현재<br>상태</b>를 나타냅니다."

                cover.boxLeft = width-150.0f
                cover.boxRight = width
            })

            result.add(EduData().apply {
                dialog.contentText = "이 부분은<br><b>하단바(내비게이션바)</b><br>입니다."
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
                dialog.contentText = "이 버튼은<br><b>최근 실행 앱</b>을<br>볼 수 있습니다."
                dialog.duration = 750

                cover.duration = 750
                cover.boxLeft = 50.0f
                cover.boxRight = 110.0f

                arrow.duration = 750
            })

            result.add(EduData().apply {
                dialog.contentText = "이 버튼은<br><b>홈 화면</b>으로<br>돌아갈 수 있습니다."

                cover.boxLeft = 175.0f
                cover.boxRight = 235.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "이 버튼은<br><b>뒤로(이전 화면)</b><br>돌아갈 수 있습니다."

                cover.boxLeft = width-110.0f
                cover.boxRight = width-50.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "<b>상단바(상태 표시줄)</b>를<br>한 번 내려볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f

                cover.boxVisibility = false
                cover.boxStrokeVisibility = false

                arrow.endTo = EduScreen.DIALOG
            })

            // TODO(상단바를 내릴 수 있는 기능 추가하기!)

            return result
        }

        // for DefaultPhoneActivity (Test)
        fun defaultPhoneTestCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.titleText = ""
                dialog.contentText = "안녕하세요!"
                dialog.contentGravity = Gravity.CENTER
                dialog.duration = 0
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "교육 진행을<br>테스트 해 볼 거에요!"
            })

            result.add(EduData().apply {
                dialog.titleText = "교육"
                dialog.titleGravity = Gravity.CENTER
                dialog.contentText = "<b>제목</b>과 <b>내용</b> 변경 테스트에요!"
                dialog.contentGravity = Gravity.END
            })

            result.add(EduData().apply {
                dialog.contentText = "사이즈 변경 테스트에요!"
                dialog.contentGravity = Gravity.START
                dialog.duration = 500
                dialog.top = 320.0f
                dialog.bottom = 320.0f
                dialog.start = 60.0f
                dialog.end = 60.0f
            })

            result.add(EduData().apply {
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 0.0f
                dialog.end = width/2
            })

            result.add(EduData().apply {
                dialog.top = 0.0f
                dialog.bottom = height/2
                dialog.start = 50.0f
                dialog.end = 50.0f
            })

            result.add(EduData().apply {
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "박스와 화살표 테스트에요!"

                cover.boxVisibility = true
                cover.boxLeft = 50.0f
                cover.boxTop = 50.0f
                cover.boxRight = width-50.0f
                cover.boxBottom = 100.0f

                arrow.duration = 500
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
            })

            result.add(EduData().apply {
                cover.duration = 500
                cover.boxRight = 100.0f
            })

            result.add(EduData().apply {
                cover.boxLeft = 75.0f
                cover.boxRight = 125.0f
            })

            result.add(EduData().apply {
                cover.boxLeft = 100.0f
                cover.boxRight = 150.0f
                cover.boxStrokeVisibility = true
            })

            result.add(EduData().apply {
                dialog.top = 275.0f
                dialog.bottom = 325.0f

                cover.duration = 0
                cover.boxTop = 500.0f
                cover.boxBottom = 550.0f

                arrow.duration = 1000
            })

            result.add(EduData().apply {
                cover.boxLeft = 150.0f
                cover.boxRight = 200.0f

                arrow.duration = 500
            })

            result.add(EduData().apply {
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false

                arrow.endTo = EduScreen.DIALOG
            })

            result.add(EduData().apply {
                dialog.contentText = "다음에 나타날 손가락이<br>가리키는 곳을 누르세요!"
            })

            result.add(EduData().apply {
                dialog.visibility = false

                cover.visibility = false

                arrow.visibility = false

                hands.add(
                    EduHand(
                        "touch",
                        R.drawable.hand,
                        110.0f,
                        290.0f,
                        50.0f,
                        75.0f,
                        -90.0f,
                        HandGestures.Companion::tapGesture
                    )
                )

                action.id = "click_key_button"
                action.message = "1"
            })

            result.add(EduData().apply {
                hands.add(
                    EduHand(
                        "touch",
                        R.drawable.hand,
                        200.0f,
                        340.0f,
                        50.0f,
                        75.0f,
                        0.0f,
                        HandGestures.Companion::tapGesture
                    )
                )

                action.id = "click_key_button"
                action.message = "2"
            })

            result.add(EduData().apply {
                dialog.contentText = "테스트 완료입니다!"
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for DefaultPhoneActivity (Before Call)
        fun defaultPhoneCourse1(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "해당 부분은 다른<br>사람의 전화번호를<br>입력하는 버튼이에요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 100.0f
                dialog.bottom = 500.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxLeft = 50.0f
                cover.boxTop = 300.0f
                cover.boxRight = width-50.0f
                cover.boxBottom = 600.0f
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.endTo = EduScreen.DIALOG
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.duration = 750
                cover.duration = 750
                arrow.duration = 750
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                cover.boxLeft = 150.0f
                cover.boxTop = 620.0f
                cover.boxRight = width-150.0f
                cover.boxBottom = 720.0f
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
                dialog.contentText = "전화를 거는 버튼입니다.<br>이 버튼을 누르면<br>상대방과 통화를<br>할 수 있어요."
            })

            result.add(EduData().apply {
                dialog.contentText = "통화를 걸어볼까요?"
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.endTo = EduScreen.DIALOG
            })

            result.add(EduData().apply {
                dialog.contentText = "010-2468-3579로<br>전화를 걸어보세요."
                dialog.contentColor = "#FFFFFF"
                dialog.background = R.drawable.edu_dialog_green_bg
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_key_button"
                action.message = "0"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 190.0f,
                    y = 580.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

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
                result.add(EduData().apply {
                    action.id = "click_key_button"
                    action.message = i.toString()
                    hands.add(
                        EduHand(
                        id = "tap",
                        x = keyPosition[i]!!.first,
                        y = keyPosition[i]!!.second,
                        gesture = HandGestures.Companion::tapGesture
                    )
                    )
                })
            }

            result.add(EduData().apply {
                action.id = "click_call_button"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 200.0f,
                    y = 670.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        // for DefaultPhoneActivity (After Call)
        fun defaultPhoneCourse2(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 500.0f
                dialog.bottom = 100.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxLeft = 150.0f
                cover.boxTop = 730.0f
                cover.boxRight = width-150.0f
                cover.boxBottom = height
                arrow.endTo = EduScreen.BOX
                dialog.visibility = true
                cover.visibility = true
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                arrow.visibility = true
                dialog.contentText = "이 부분을 통해<br>방금 전화를 건<br>통화 목록을<br>확인할 수 있어요."
            })

            result.add(EduData().apply {
                dialog.contentText = "최근기록을 눌러주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.visibility = false
                action.id = "click_recent_history_button"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 250.0f,
                    y = 670.0f,
                    rotation = 225.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            result.add(EduData().apply {
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
                arrow.endTo = EduScreen.DIALOG
                dialog.contentText = "최근기록을 보면<br>전화 성공/실패 여부나<br>영상 통화 여부 등을<br>확인할 수 있어요."
            })

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
                dialog.contentText = "이 부분은<br>전화번호를 저장해서<br>편리하게 전화를<br>걸 수 있어요."
            })

            result.add(EduData().apply {
                dialog.contentText = "연락처를 눌러주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.visibility = false
                action.id = "click_contact_button"
                hands.add(
                    EduHand(
                        id = "tap",
                        x = 250.0f,
                        y = 670.0f,
                        rotation = 225.0f,
                        gesture = HandGestures.Companion::tapGesture
                    )
                )
            })

            return result
        }

        // for DefaultPhoneActivity (After ContactAddition)
        fun defaultPhoneCourse3(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
            })

            return result
        }

        //for DefaultPhoneContactFragmentActivity



        // for DefaultPhoneCallActivity
        fun defaultPhoneCallCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "전화 연결에 성공하였습니다!"
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.background = R.drawable.edu_dialog_yellow_bg
                dialog.visibility = true

                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "지금 현재 상대방에게<br>통화하고 있는 중이에요."
                dialog.background = R.drawable.edu_dialog_bg
            })

            result.add(EduData().apply {
                dialog.contentText = "근데 상대방이 전화를<br>안 받는 것 같아요.<br>통화를 끊어볼까요?"
            })

            result.add(EduData().apply {
                dialog.visibility = false

                cover.visibility = false

                action.id = "click_hang_up_button"

                hands.add(
                    EduHand(
                    id = "tap",
                    x = 190.0f,
                    y = 700.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            result.add(EduData())

            return result
        }

        // for CameraActivity
        fun cameraCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "위 메뉴는 카메라의<br>플래시, 타이머 등을<br>설정할 수 있어요."
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

            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "아래 버튼은 사진,<br>동영상 등 자신이<br>원하는 촬영을<br>선택할 수 있어요."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                cover.boxLeft = 30.0f
                cover.boxTop = 620.0f
                cover.boxRight = width-30.0f
                cover.boxBottom = 650.0f
            })

            result.add(EduData().apply {
                cover.duration = 750
                dialog.contentText = "촬영할 수 있는<br>버튼이에요.<br>버튼을 클릭하면<br>사진 저장이 돼요."
                dialog.top = 400.0f
                dialog.bottom = 200.0f
                cover.boxLeft = 160.0f
                cover.boxTop = height-105.0f
                cover.boxRight = width-160.0f
                cover.boxBottom = height-15.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "찍은 사진을<br>확인할 수 있는<br>앨범이에요."
                cover.boxLeft = 55.0f
                cover.boxTop = height-105.0f
                cover.boxRight = 135.0f
                cover.boxBottom = height-15.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "사진을 찍으면<br>이 부분에 나타나요."
            })

            result.add(EduData().apply {
                dialog.contentText = "화면을 전환하는<br>부분이에요."
                cover.boxLeft = width-135.0f
                cover.boxTop = height-105.0f
                cover.boxRight = width-55.0f
                cover.boxBottom = height-15.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "버튼을 클릭하면<br>나를 찍을 수도 있고,<br>상대방을 찍을 수도<br>있어요."
                dialog.top = 350.0f
            })

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

            result.add(EduData().apply {
                dialog.contentText = "손가락을 따라<br>클릭해주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_shooting_button"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 220.0f,
                    y = height-100.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            result.add(EduData().apply {
                dialog.duration = 0
                cover.duration = 0
                arrow.duration = 0
                dialog.contentText = "방금 찍은 사진은<br>여기에서 확인할 수<br>있어요."
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

            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "클릭해주세요."
                dialog.top = 450.0f
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.visibility = false
                action.id = "click_gallery_view_button"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 120.0f,
                    y = height-100.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        // for DefaultMessageActivity
        fun defaultMessageCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "여기는 채팅방으로<br>상대방과 메시지를<br>주고받을 수 있어요."
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
                dialog.contentText = "여기서 문자 메시지를<br>입력할 수 있어요."
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
                dialog.contentText = "이 버튼을 누르면<br>메시지가 전송돼요."
                cover.boxLeft = width-55.0f
                cover.boxRight = width
            })

            result.add(EduData().apply {
                dialog.contentText = "시너지에게<br>메시지를 보내볼까요?"
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
                hands.add(
                    EduHand(
                    id = "tap",
                    x = width/2,
                    y = height-110.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            result.add(EduData().apply {
                action.id = "click_send_button"
            })

            result.add(EduData().apply {
                dialog.duration = 0
                cover.duration = 0
                dialog.contentText = "문자 메시지를<br>성공적으로 보냈습니다!"
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
                dialog.contentText = "자신이 보낸 메시지는<br>아래에 보이는 것처럼<br>확인할 수 있어요."
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
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "여기는 그동안<br>주고받은 메시지를<br>확인할 수 있어요."
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
                dialog.contentText = "이 버튼은 새로운<br>메시지를 작성할 수 있어요."
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
                hands.add(
                    EduHand(
                    id = "tap",
                    x = width/2,
                    y = 170.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        fun kakaoCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "<span style=\"color:#E6C60D\"><b>카카오톡</b></span>의<br>메인 화면입니다."
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
                dialog.top = 200.0f
                dialog.bottom = 400.0f
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                cover.boxLeft = 225.0f
                cover.boxTop = 15.0f
                cover.boxRight = 275.0f
                cover.boxBottom = 65.0f
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
                dialog.contentText = "이 버튼은 빠르게<br>친구를 찾을 수 있는<br>버튼이에요."
            })

            result.add(EduData().apply {
                dialog.contentText = "친구를 빠르게 찾고<br>싶을 때 이 버튼을 눌러<br>찾고 싶은 친구의 이름을<br>입력하면 돼요."
            })

            result.add(EduData().apply {
                cover.boxLeft = 10.0f
                cover.boxTop = 75.0f
                cover.boxRight = width-10.0f
                cover.boxBottom = 135.0f
                dialog.contentText = "내 프로필입니다."
            })

            result.add(EduData().apply {
                dialog.titleText = "프로필이란?"
                dialog.contentText = "카카오톡을 사용할 때<br>다른 사람들에게<br>여러분을 알리는데<br>도움을 줄 수 있는<br>간단한 자기소개입니다."
                dialog.bottom = 300.0f
            })

            result.add(EduData().apply {
                dialog.titleText = ""
                dialog.contentText = "친구들의 목록을 볼 수 있어요."
                dialog.top = 25.0f
                dialog.bottom = 625.0f
                cover.boxTop = 175.0f
                cover.boxBottom = 700.0f
                arrow.endTo = EduScreen.DIALOG
            })

            result.add(EduData().apply {
                dialog.contentText = "친구를 찾을 수 있는<br>버튼입니다."
                dialog.duration = 750
                arrow.duration = 750
                dialog.top = 450.0f
                dialog.bottom = 150.0f
                dialog.start = 25.0f
                dialog.end = 75.0f
                cover.boxLeft = 25.0f
                cover.boxTop = height-70.0f
                cover.boxRight = 80.0f
                cover.boxBottom = height-0.0f
                arrow.endTo = EduScreen.BOX
            })

            result.add(EduData().apply {
                dialog.duration = 750
                arrow.duration = 750
                dialog.contentText = "방금까지 살펴본<br>카카오톡의 화면이<br>이 버튼을 누르면<br>보이는 화면입니다."
                dialog.top = 425.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "카카오톡으로<br>친구와 연락을<br>주고 받아 볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.endTo = EduScreen.DIALOG
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 100.0f,
                    y = 200.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        fun kakaoProfileCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "친구의 프로필입니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "'1:1 채팅'은 친구와<br>대화할 수 있는 기능입니다."
                dialog.duration = 750
                dialog.top = 400.0f
                dialog.bottom = 200.0f
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
                cover.boxLeft = 25.0f
                cover.boxTop = height-95.0f
                cover.boxRight = 110.0f
                cover.boxBottom = height-10.0f
                arrow.duration = 750
                arrow.endTo = EduScreen.BOX
                arrow.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "'보이스톡'은 친구의<br>전화 번호가 없어도<br>전화를 할 수 있습니다."
                cover.duration = 750
                cover.boxLeft = 165.0f
                cover.boxRight = 250.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "와이파이가 없는<br>곳에서는 데이터가<br>나갈 수 있으니<br>주의해주세요!"
            })

            result.add(EduData().apply {
                dialog.contentText = "페이스톡은 영상통화입니다."
                cover.boxLeft = width-110.0f
                cover.boxRight = width-25.0f
            })

            result.add(EduData().apply {
                dialog.contentText = "보이스톡과 같이<br>와이파이가 없으면<br>데이터가 나가니<br>주의해주세요."
            })

            result.add(EduData().apply {
                dialog.contentText = "그럼 1:1 대화를<br>시작해볼까요?"
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                cover.duration = 0
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                arrow.endTo = EduScreen.DIALOG
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_chat11_button"
                hands.add(
                    EduHand(
                    id = "tap",
                    source = R.drawable.hand,
                    x = 85.0f,
                    y = 675.0f,
                    width = 50.0f,
                    height = 75.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        fun kakaoChatCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "이 공간은<br>친구와 채팅을<br>할 수 있습니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            /*
            dialog.visibility = false
            cover.visibility = false
            arrow.visibility = false
            action.id = "click_message_edit_text"
            hands.add(EduHand(
                id = "tap",
                source = R.drawable.hand,
                x = 200.0f,
                y = 675.0f,
                rotation = 180.0f,
                gesture = HandGestures::tapGesture
            ))
            */

            result.add(EduData().apply {
                dialog.contentText = "메시지를 보내볼까요?"
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                arrow.visibility = false
                action.id = "click_message_edit_text"
                hands.add(
                    EduHand(
                    id = "tap",
                    source = R.drawable.hand,
                    x = 200.0f,
                    y = 675.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        // activity_setting2_main
        fun settingsCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "화면 밝기와<br>글자 크기에 대해서<br>배워보겠습니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "맨 아래까지 스크롤해주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                action.id = "scroll_to_bottom"
                hands.add(
                    EduHand(
                    id = "drag",
                    x = 200.0f,
                    y = 650.0f,
                    gesture = HandGestures.Companion::settingsScrollDownGesture
                )
                )
            })

            result.add(EduData().apply {
                dialog.contentText = "<b>디스플레이</b>는<br>스마트폰의 다양한 설정을 할 수<br>있습니다."
                dialog.top = 450.0f
                dialog.bottom = 150.0f
                cover.boxLeft = 20.0f
                cover.boxTop = 320.0f
                cover.boxRight = width-20.0f
                cover.boxBottom = 390.0f
                dialog.visibility = true
                cover.visibility = true
                cover.boxVisibility = true
                cover.boxStrokeVisibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "디스플레이를 클릭해주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                cover.boxVisibility = false
                cover.boxStrokeVisibility = false
                action.id = "tap_display_item"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = width/2,
                    y = 360.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        fun settingsDisplayCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "먼저 스마트폰<br>밝기 조절을<br>배워보겠습니다."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "손가락을 따라<br>움직여주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                action.id = "change_light_bar"
                action.message = "100"
                hands.add(
                    EduHand(
                    id = "drag",
                    x = 220.0f,
                    y = 510.0f,
                    gesture = HandGestures.Companion::displayLightDragGesture
                )
                )
            })

            result.add(EduData().apply {
                dialog.contentText = "이번엔 글자 크기를<br>조절해보겠습니다."
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.contentText = "맨 아래까지 스크롤해주세요."
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                action.id = "scroll_to_bottom"
                hands.add(
                    EduHand(
                    id = "drag",
                    source = R.drawable.hand,
                    x = 200.0f,
                    y = 650.0f,
                    width = 50.0f,
                    height = 75.0f,
                    gesture = HandGestures.Companion::displayScrollGesture
                )
                )
            })

            result.add(EduData().apply {
                action.id = "tap_font_item"
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 250.0f,
                    y = 475.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        fun settingsFontCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "손가락을 따라<br>움직여주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                action.id = "change_text_size_bar"
                action.message = "7"
                hands.add(
                    EduHand(
                    id = "drag",
                    x = 40.0f,
                    y = 660.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::textSizeDragGesture
                )
                )
            })

            result.add(EduData().apply {
                dialog.visibility = true
                cover.visibility = true
                dialog.contentText = "이것으로<br>환경 설정 교육을<br>마치겠습니다.<br>수고하셨습니다!"
            })

            return result
        }

        // for GoogleMainActivity
        fun googleMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "로그인 버튼을<br>클릭해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                hands.add(
                    EduHand(
                    id = "tap",
                    x = width-60.0f,
                    y = 50.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        // for GoogleDefaultInfoActivity
        fun googleDefaultInfoCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "기본 정보를<br>입력해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleGetCodeActivity
        fun googleGetCodeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "전화번호를<br>입력해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleLoginActivity
        fun googleLoginCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "<b>계정 만들기</b>를 <br>클릭해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
                hands.add(
                    EduHand(
                    id = "tap",
                    x = 40.0f,
                    y = 520.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
                )
            })

            return result
        }

        // for GoogleMailActivity
        fun googleMailCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "원하는 아이디를<br>만들어주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GoogleMailAddActivity
        fun googleMailAddCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
            })

            return result
        }

        // for GoogleMakeActivity
        fun googleMakeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "자신의 이름을<br>작성해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GooglePasswordActivity
        fun googlePasswordCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "비밀번호를<br>생성해주세요."
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for GooglePutCodeActivity
        fun googlePutCodeCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "보안문자가 올 때까지<br>기다려볼까요?"
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true
                cover.visibility = true
            })

            result.add(EduData().apply {
                dialog.visibility = false
                cover.visibility = false
            })

            return result
        }

        // for installMainActivity
        fun installMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for installActivity
        fun installCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for DlvMainActivity
        fun dlvMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for foodmenuActivity
        fun foodmenuCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for payDActivity
        fun payDCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for shopActivity
        fun shopCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for mapActivity
        fun mapCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for payActivity
        fun payCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for payMActivity
        fun payMCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for TaxiMainActivity
        fun taxiMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for Ticket2MainActivity
        fun resCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for installActivity
        fun ticket2MainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for TicketMainActivity
        fun ticketMainCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }

        // for TpayActivity
        fun TPayCourse(context: Context, widthDp: Float, heightDp: Float): ArrayList<EduData> {
            val width = DisplayUtils.pxToDp(context, widthDp)
            val height = DisplayUtils.pxToDp(context, heightDp)
            val result = ArrayList<EduData>()

            result.add(EduData().apply {
                dialog.contentText = "넌 못 지나간다."
                dialog.top = 300.0f
                dialog.bottom = 300.0f
                dialog.start = 50.0f
                dialog.end = 50.0f
                dialog.visibility = true

                cover.visibility = true
            })

            return result
        }
    }
}
