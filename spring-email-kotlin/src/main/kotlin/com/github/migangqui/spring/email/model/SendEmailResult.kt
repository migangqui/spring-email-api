package com.github.migangqui.spring.email.model

data class SendEmailResult(
    val status: Int = 0,
    val cause: String? = null,
    val exception: Exception? = null
)
