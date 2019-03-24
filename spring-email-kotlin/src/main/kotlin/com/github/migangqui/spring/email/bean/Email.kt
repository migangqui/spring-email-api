package com.github.migangqui.spring.email.bean

import java.io.InputStream

data class Email(
    val from: String,
    val to: String,
    val subject: String? = null,
    val body: String,
    val file: InputStream? = null,
    val filename: String? = null
)