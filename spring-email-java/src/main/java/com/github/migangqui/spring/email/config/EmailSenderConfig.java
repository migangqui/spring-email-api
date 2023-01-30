package com.github.migangqui.spring.email.config;

import com.github.migangqui.spring.email.service.EmailSender;
import com.github.migangqui.spring.email.service.EmailSenderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailSenderConfig {

    @Bean
    public EmailSender emailSender(final JavaMailSender javaMailSender) {
        return new EmailSenderImpl(javaMailSender);
    }
}
