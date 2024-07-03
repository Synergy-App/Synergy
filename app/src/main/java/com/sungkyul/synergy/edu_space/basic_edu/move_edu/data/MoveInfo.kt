package com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.move_edu.data

import java.io.Serializable

data class MoveInfo (
    val moveImageResId: Int,
    val moveText: String,
    val moveDescription: String
) : Serializable