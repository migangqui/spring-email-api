package com.github.migangqui.spring.email.service

import com.github.migangqui.spring.email.model.Email
import com.github.migangqui.spring.email.model.SendEmailResult
import java.util.concurrent.Future

interface EmailSender {
    fun send(email: Email): SendEmailResult
    fun sendAsync(email: Email): Future<SendEmailResult>
}