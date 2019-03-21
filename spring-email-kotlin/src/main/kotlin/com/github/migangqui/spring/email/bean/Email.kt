package com.github.migangqui.spring.email.bean

import java.io.InputStream

data class Email(
    val from: String? = null,
    val to: String? = null,
    val subject: String? = null,
    val body: String? = null,
    val file: InputStream? = null,
    val filename: String? = null
)