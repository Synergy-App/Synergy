package com.sungkyul.synergy.types

import com.google.gson.annotations.SerializedName

data class ExamCheckResult(
    @SerializedName("correct")
    val correct: Boolean,
    @SerializedName("correct_answer")
    val correctAnswer: Int?
)
