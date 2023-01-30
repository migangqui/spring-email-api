package com.github.migangqui.spring.email.model

import java.io.InputStream

data class EmailAttachment(
    val file: InputStream,
    val filename: String
)