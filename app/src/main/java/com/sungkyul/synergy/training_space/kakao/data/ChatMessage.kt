package com.sungkyul.synergy.training_space.kakao.data

import java.io.Serializable

data class ChatMessage2(val sender: String, val message: String, val timestamp: String)

data class profileItem2(val image: Int, val name: String, val message: String) : Serializable
