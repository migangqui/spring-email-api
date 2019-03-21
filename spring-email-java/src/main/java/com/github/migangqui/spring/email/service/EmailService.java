package com.github.migangqui.spring.email.service;

import com.github.migangqui.spring.email.bean.SendEmailResult;
import com.github.migangqui.spring.email.bean.Email;

import java.util.concurrent.Future;

public interface EmailService {
    SendEmailResult send(Email emailDTO);
    
    Future<SendEmailResult> sendAsync(Email emailDTO);
}
