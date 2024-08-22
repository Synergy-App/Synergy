package com.sungkyul.synergy.com.sungkyul.synergy.learning_space

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultPair(
    val questionNumber: Int,
    val isCorrect: Boolean,
    val userAnswer: Int,
    val answerOnSelect: Int,
    val educationId: Int // 추가된 필드
) : Parcelable
