package com.sungkyul.synergy.kakaotalk


import java.io.Serializable
/** 프로필 데이터 */
data class profileItem(val image: Int, val name: String, val message: String) : Serializable
