package com.github.migangqui.spring.email.model

data class Email(
    val from: String,
    val to: String,
    val subject: String? = null,
    val body: String,
    val attachment: EmailAttachment?
)