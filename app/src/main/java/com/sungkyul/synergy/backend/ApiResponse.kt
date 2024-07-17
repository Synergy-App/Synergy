package com.sungkyul.synergy.backend

data class ResponseError (
    val msg: String,
    val code: String
)
/** API 응답데이터 양식 */
data class ApiResponse<T> (
    /** API 처리 성공여부 */
    val success: Boolean,
    /** 처리 실패 시 서버에서 응답하는 에러 객체 */
    val err: ResponseError,
    /** 처리 성공 시 서버에서 응답하는 응답데이터 */
    val data: T,
    val message: String?
)