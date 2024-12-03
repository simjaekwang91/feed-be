package com.liner.backend.exception

import com.liner.backend.model.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun exceptionHandler(exception: Exception): ResponseEntity<*> {
        logger.error(exception.message, exception)
        return ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun redisExceptionHandler(exception: RedisException): ResponseEntity<ErrorResponse> {
        logger.error(exception.message, exception)
        val errorResponse = ErrorResponse (
            errorMessage = exception.errorType.errorMessage,
            errorCode = exception.errorType.code,
            errorType = exception.errorType.name
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun businessRuleExceptionHandler(exception: BusinessRuleException): ResponseEntity<ErrorResponse> {
        logger.error(exception.message, exception)
        val errorResponse = ErrorResponse (
            errorMessage = exception.errorType.errorMessage,
            errorCode = exception.errorType.code,
            errorType = exception.errorType.name
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
