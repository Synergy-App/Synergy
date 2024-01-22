package com.sungkyul.synergy.data

import java.io.Serializable

data class IconInfo(
    val iconImageResId: Int,
    val iconText: String,
    val iconDescription: String
) : Serializable

