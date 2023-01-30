package com.github.migangqui.spring.email.service

import com.github.migangqui.spring.email.model.Email
import com.github.migangqui.spring.email.model.SendEmailResult
import org.slf4j.LoggerFactory
import org.springframework.core.io.InputStreamResource
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import java.util.concurrent.Future
import javax.mail.MessagingException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

internal class EmailSenderImpl(private val javaMailSender: JavaMailSender) : EmailSender {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun send(email: Email): SendEmailResult {
        log.info("Sending email")
        return try {
            javaMailSender.send(generateMailMessage(email))
            log.debug("Email sent successfully")
            SendEmailResult(status = 200)
        } catch (exception: MessagingException) {
            log.warn("An error has occurred sending email", exception)
            SendEmailResult(status = 500, cause = exception.message, exception = exception)
        } catch (exception: MailException) {
            log.warn("An error has occurred sending email", exception)
            SendEmailResult(status = 500, cause = exception.message, exception = exception)
        }
    }

    @Async
    override fun sendAsync(email: Email): Future<SendEmailResult> {
        return AsyncResult(send(email))
    }

    /* Private methods */

    @Throws(MessagingException::class)
    private fun generateMailMessage(email: Email): MimeMessage {
        val helper = MimeMessageHelper(javaMailSender.createMimeMessage(), email.attachment != null)

        helper.setFrom(InternetAddress(email.from))
        helper.setTo(email.to)
        helper.setSubject(email.subject!!)
        helper.setText(email.body, true)

        if (email.attachment != null) {
            helper.addAttachment(email.attachment.filename, InputStreamResource(email.attachment.file))
        }
        return helper.mimeMessage
    }

}