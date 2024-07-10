package com.sungkyul.synergy.com.sungkyul.synergy.edu_space.basic_edu.dictionary_edu.data

import java.io.Serializable

data class IconInfo(
    val iconImageResId: Int,
    val iconText: String,
    val iconDescription: String
) : Serializable

