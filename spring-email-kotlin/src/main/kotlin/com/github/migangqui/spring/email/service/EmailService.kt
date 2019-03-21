package com.github.migangqui.spring.email.service

import com.github.migangqui.spring.email.bean.Email
import com.github.migangqui.spring.email.bean.SendEmailResult
import java.util.concurrent.Future

interface EmailService {
    fun send(email: Email): SendEmailResult

    fun sendAsync(email: Email): Future<SendEmailResult>
}