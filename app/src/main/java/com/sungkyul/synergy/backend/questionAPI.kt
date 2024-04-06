package com.sungkyul.synergy.backend
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface questionAPI {
    @GET("quiz/question_api.php")
    fun getQuestion(): Call<List<QuizQuestion>>

    @GET("quiz/learning_space_info.php")
    fun getLearningSpaceInfo(): Call<List<QuizInfo>>
}
