package com.github.migangqui.spring.email.config

import com.github.migangqui.spring.email.service.EmailService
import com.github.migangqui.spring.email.service.EmailServiceImpl
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Slf4j
@Configuration
class EmailServiceConfig {
    
    @Bean
    fun emailService(javaMailSender: JavaMailSender): EmailService {
        return EmailServiceImpl(javaMailSender)
    }
}