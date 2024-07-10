package com.sungkyul.synergy.profile_space

import android.os.Parcelable
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamResult(
    val title: String,
    val title2: String,
    val result: String,
    val imageResId: Int,
    val resultList: List<ResultPair>
) : Parcelable
