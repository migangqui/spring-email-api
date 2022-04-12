package com.github.migangqui.spring.email.config

import com.github.migangqui.spring.email.service.EmailSender
import com.github.migangqui.spring.email.service.EmailSenderImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class EmailSenderConfig {
    
    @Bean
    fun emailSender(javaMailSender: JavaMailSender): EmailSender {
        return EmailSenderImpl(javaMailSender)
    }
}