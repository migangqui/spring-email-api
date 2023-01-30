package com.github.migangqui.spring.email.service;

import com.github.migangqui.spring.email.model.Email;
import com.github.migangqui.spring.email.model.SendEmailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Future;

public class EmailSenderImpl implements EmailSender {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderImpl.class);

    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public SendEmailResult send(final Email email) {
        log.info("Sending email");
        try {
            javaMailSender.send(generateMailMessage(email));
            log.debug("Email sent successfully");
            return new SendEmailResult(200);
        } catch (final MessagingException | MailException exception) {
            log.warn("An error has occurred sending email", exception);
            return new SendEmailResult(500, exception.getMessage(), exception);
        }
    }

    @Async
    @Override
    public Future<SendEmailResult> sendAsync(final Email email) {
        return new AsyncResult<>(send(email));
    }

    /* Private methods */

    private MimeMessage generateMailMessage(final Email email) throws MessagingException {
        final MimeMessageHelper helper = new MimeMessageHelper(
                javaMailSender.createMimeMessage(), email.attachment().isPresent());

        helper.setFrom(new InternetAddress(email.from()));
        helper.setTo(email.to());
        helper.setSubject(email.subject());
        helper.setText(email.body(), true);

        if (email.attachment().isPresent()) {
            helper.addAttachment(email.attachment().get().filename(),
                    new InputStreamResource(email.attachment().get().file()));
        }

        return helper.getMimeMessage();
    }

}
