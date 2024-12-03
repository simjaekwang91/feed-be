package com.liner.backend.model.response

data class ErrorResponse (
    val errorMessage: String? = null,
    val errorType: String? = null,
    val errorCode: Int? = null,
)
