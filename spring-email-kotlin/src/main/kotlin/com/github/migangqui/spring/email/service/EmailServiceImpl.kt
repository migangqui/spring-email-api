package com.github.migangqui.spring.email.service

import com.github.migangqui.spring.email.bean.Email
import com.github.migangqui.spring.email.bean.SendEmailResult
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import java.util.concurrent.Future
import javax.mail.MessagingException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class EmailServiceImpl(private val javaMailSender: JavaMailSender) : EmailService {

    private val log = KotlinLogging.logger {}

    override fun send(email: Email): SendEmailResult {
        log.info("Sending email to {}", email.to)
        return try {
            val generatedMailMessage = generateMailMessage(email)
            javaMailSender.send(generatedMailMessage)
            log.debug("Email sent successfully to {}", email.to)
            SendEmailResult(status = 200)
        } catch (e: MessagingException) {
            log.warn("An error has ocurred sending email", e)
            SendEmailResult(status = 500, cause = e.message, exception = e)
        } catch (e: MailException) {
            log.warn("An error has ocurred sending email", e)
            SendEmailResult(status = 500, cause = e.message, exception = e)
        }
    }

    @Async
    override fun sendAsync(email: Email): Future<SendEmailResult> {
        return AsyncResult(send(email))
    }

    /* Private methods */

    @Throws(MessagingException::class)
    private fun generateMailMessage(email: Email): MimeMessage {
        val generateMailMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(generateMailMessage, email.file != null)
        helper.setFrom(InternetAddress(email.from!!))
        helper.setTo(email.to!!)
        helper.setSubject(email.subject!!)
        helper.setText(email.body!!, true)
        if (email.file != null && email.filename != null) {
            helper.addAttachment(email.filename, InputStreamResource(email.file))
        }
        return helper.mimeMessage
    }

}