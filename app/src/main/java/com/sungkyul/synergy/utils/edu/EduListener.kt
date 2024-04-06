package com.sungkyul.synergy.utils.edu

interface EduListener {
    // 프래그먼트에서 binding.post가 처리되었을 때 실행되는 함수
    fun onPosted()

    // 액션(버튼 클릭, 텍스트 입력 등)이 발생했을 때 실행되는 함수
    fun onAction(id: String, message: String? = null): Boolean
}
