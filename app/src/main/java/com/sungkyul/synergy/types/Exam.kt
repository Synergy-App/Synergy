package com.sungkyul.synergy.types

data class Exam(
    /**시험 관련**/
    val id: Int,
    val createdAt: String,
    val educationId: Int,
    val question: String,
    val description: String?,
    val descriptionImgUrl: String?,
    val step: Int,
    val kind: String,
    val select1: String?,
    val select2: String?,
    val select3: String?,
    val select4: String?,
    val select5: String?,
    val select1ImgUrl: String?,
    val select2ImgUrl: String?,
    val select3ImgUrl: String?,
    val select4ImgUrl: String?,
    val select5ImgUrl: String?
)