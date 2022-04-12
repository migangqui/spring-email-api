package com.github.migangqui.spring.email.config;

import com.github.migangqui.spring.email.service.EmailSender;
import com.github.migangqui.spring.email.service.EmailSenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@Configuration
public class EmailSenderConfig {

    @Bean
    public EmailSender emailSender(final JavaMailSender javaMailSender) {
        log.debug("Registering EmailSender bean");
        return new EmailSenderImpl(javaMailSender);
    }
}
