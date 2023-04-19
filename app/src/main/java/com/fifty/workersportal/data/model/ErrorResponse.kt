package com.fifty.workersportal.data.model

data class ErrorResponse(
    val code:Int,
    val status: Boolean,
    val message: String,
    val errors: List<String>
)
