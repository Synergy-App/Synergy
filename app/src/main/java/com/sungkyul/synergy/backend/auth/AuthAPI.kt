package com.sungkyul.synergy.backend.auth

import com.google.firebase.auth.UserInfo
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.types.Exam
import com.sungkyul.synergy.types.ExamAnswerBody
import com.sungkyul.synergy.types.ExamCheckResult
import com.sungkyul.synergy.types.ExamListResponse
import com.sungkyul.synergy.types.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {

    @GET("user/me")
    fun getUserInfo(@Header("Authorization") token: String): Call<ApiResponse<User>>


    @GET("education/{educationId}/exams")
    fun getExamListByEducationId(@Path("educationId") educationId: Int): Call<ApiResponse<ExamListResponse>>


    @GET("exam")
    fun getExamList(): Call<ApiResponse<ExamListResponse>>

    @GET("exams/{id}")
    fun getExamDetails(
        @Header("Authorization") token: String,
        @Path("id") examId: Int
    ): Call<ApiResponse<Exam>>

    @POST("exam/{id}/check")
    fun checkExamAnswer(
        @Header("Authorization") token: String,
        @Path("id") examId: Int,
        @Body answer: ExamAnswerBody
    ): Call<ApiResponse<ExamCheckResult>>


    @POST("user/signup")
    suspend fun signup(@Body requestBody: SignUpBody): Call<ApiResponse<Nothing>>

    @POST("user/check-id")
    fun checkId(@Body requestBody: CheckIdBody): Call<ApiResponse<CheckResult>>

    @POST("user/check-nickname")
    fun checkNickname(@Body requestBody: CheckNicknameBody): Call<ApiResponse<CheckResult>>

    @POST("user/signin")
    fun signin(@Body requestBody: SignInBody): Call<ApiResponse<SignInResult>>

    @POST("user/find-id")
    fun findIdByPhone(@Body requestBody: FindIdBody): Call<ApiResponse<FindIdResult>>

    @POST("user/verify-user")
    fun verifyUser(@Body requestBody: VerifyUserBody): Call<ApiResponse<VerifyUserResult>>

    @POST("user/change-password")
    fun changePassword(@Body requestBody: ChangePasswordBody): Call<ApiResponse<ChangePasswordResult>>

/*    @GET("user/info")
    fun getUserInfo(@Header("Authorization") token: String): Call<ApiResponse<UserInfo>>*/
}
