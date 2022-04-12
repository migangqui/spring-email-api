package com.github.migangqui.spring.email.service;

import com.github.migangqui.spring.email.model.SendEmailResult;
import com.github.migangqui.spring.email.model.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public SendEmailResult send(final Email email) {
        log.debug("Sending email");
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
                javaMailSender.createMimeMessage(), email.file().isPresent());

        helper.setFrom(new InternetAddress(email.from()));
        helper.setTo(email.to());
        helper.setSubject(email.subject());
        helper.setText(email.body(), true);

        if (email.file().isPresent()) {
            helper.addAttachment(email.filename().get(), new InputStreamResource(email.file().get()));
        }

        return helper.getMimeMessage();
    }

}
