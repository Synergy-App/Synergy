package com.sungkyul.synergy.com.sungkyul.synergy.learning_space

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultPair(
    val questionNumber: Int,
    val isCorrect: Boolean,
    val userAnswer: Int,       // 사용자 선택 답변
    val correctAnswer: Int     // 정답
) : Parcelable
