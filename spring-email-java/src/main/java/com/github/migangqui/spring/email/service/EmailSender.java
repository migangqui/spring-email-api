package com.github.migangqui.spring.email.service;

import com.github.migangqui.spring.email.model.SendEmailResult;
import com.github.migangqui.spring.email.model.Email;

import java.util.concurrent.Future;

public interface EmailSender {
    SendEmailResult send(Email email);
    Future<SendEmailResult> sendAsync(Email email);
}
