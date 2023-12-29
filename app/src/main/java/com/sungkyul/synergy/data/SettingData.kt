package com.sungkyul.synergy.data

import java.io.Serializable
/** 교육공간 속 환경설정교육 리사이클러뷰 데이터 */
data class SettingData(
    val name: String,
    val explain: String,
    val img: Int
) : Serializable