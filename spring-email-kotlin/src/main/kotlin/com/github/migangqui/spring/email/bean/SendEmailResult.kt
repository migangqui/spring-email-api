package com.github.migangqui.spring.email.bean

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(NON_NULL)
data class SendEmailResult(
    val status: Int = 0,
    val cause: String? = null,
    val exception: Exception? = null
)
