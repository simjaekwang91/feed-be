package com.liner.backend.exception

class RedisException (
    val errorType: RedisErrorType
):RuntimeException()

enum class RedisErrorType(val errorMessage: String, val code: Int) {
    UNKNOWN("정의되지 않은 에러입니다.",9999),
    KEY_IS_NOT_EMPTY("키는 비어있을 수 없습니다.", 9998)
}
