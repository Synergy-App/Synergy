package com.sungkyul.synergy.edu_space.move_edu.data

import java.io.Serializable

data class MoveInfo (
    val moveImageResId: Int,
    val moveText: String,
    val moveDescription: String
) : Serializable