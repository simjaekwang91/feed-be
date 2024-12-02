package com.liner.backend.exception

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
}
