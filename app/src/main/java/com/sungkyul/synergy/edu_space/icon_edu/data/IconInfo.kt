package com.sungkyul.synergy.edu_space.icon_edu.data

import java.io.Serializable

data class IconInfo(
    val iconImageResId: Int,
    val iconText: String,
    val iconDescription: String
) : Serializable

