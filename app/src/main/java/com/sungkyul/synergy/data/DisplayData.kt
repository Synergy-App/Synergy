package com.sungkyul.synergy.data

import java.io.Serializable
/** 교육공간 속 환경설정교육 속 디스플레이 리사이클러뷰 데이터 */

data class DisplayData (
    val dname : String,
    val dexplain : String,
) : Serializable