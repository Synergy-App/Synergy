package com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.move_edu.data

import java.io.Serializable

data class Move(
    val moveImage: Int,
    val moveText: String,
    val currentIndex: Int
) : Serializable
