package com.sungkyul.synergy.util

interface EduListener {
    // 프래그먼트의 onCreateView가 호출되었을 때 실행되는 함수
    fun onCreateViewFinished()

    // 연습 중에 어떠한 액션(버튼 클릭, 텍스트 입력 등)이 발생했을 때 실행되는 함수
    fun onAction(actionId: String, actionMsg: String? = null)
}
