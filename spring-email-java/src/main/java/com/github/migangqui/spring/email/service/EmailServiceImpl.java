package com.github.migangqui.spring.email.service;

import com.github.migangqui.spring.email.bean.SendEmailResult;
import com.github.migangqui.spring.email.bean.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Future;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public SendEmailResult send(Email email) {
        SendEmailResult result;
        log.info("Sending email to {}", email.getTo());
        try {
            MimeMessage generatedMailMessage = generateMailMessage(email);
            javaMailSender.send(generatedMailMessage);
            log.debug("Email sent successfully to {}", email.getTo());
            result = SendEmailResult.builder().status(200).build();
        } catch (MessagingException | MailException e) {
            log.warn("An error has ocurred sending email", e);
            result = SendEmailResult.builder()
                    .status(500).cause(e.getMessage()).exception(e)
                    .build();
        }
        return result;
    }

    @Async
    @Override
    public Future<SendEmailResult> sendAsync(Email email) {
        return new AsyncResult<>(send(email));
    }

    /* Private methods */

    private MimeMessage generateMailMessage(Email email) throws MessagingException {
        MimeMessage generateMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(generateMailMessage, email.getFile() != null);;
        helper.setFrom(new InternetAddress(email.getFrom()));
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);
        if (email.getFile() != null) {
            helper.addAttachment(email.getFilename(), new InputStreamResource(email.getFile()));
        }
        return helper.getMimeMessage();
    }

}
