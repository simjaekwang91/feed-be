package com.liner.backend.exception

class BusinessRuleException(
    val errorType: BusinessErrorType
):RuntimeException()

enum class BusinessErrorType(val errorMessage: String, val code: Int) {
    UNKNOWN("정의되지 않은 에러입니다.",9999),
    NOT_BLANK_USER_ID("사용자 id는 빈 값일 수 없습니다.", 9998),
    NOT_EMPTY_PAGE_LIST("페이지 리스트가 비어 있을 수 없습니다.", 8888)
}
