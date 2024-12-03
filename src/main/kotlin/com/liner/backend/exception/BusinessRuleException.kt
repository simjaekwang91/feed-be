package com.liner.backend.exception

class BusinessRuleException(
    val errorType: BusinessErrorType
):RuntimeException()

enum class BusinessErrorType(val errorMessage: String, val code: Int) {
    UNKNOWN("정의되지 않은 에러입니다.",9999),
    NOT_FOUND_USER("찾을 수 없는 사용자 입니다.", 9998)
}
