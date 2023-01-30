package com.github.migangqui.spring.email.java.example;

import com.github.migangqui.spring.email.model.Email;
import com.github.migangqui.spring.email.model.SendEmailResult;
import com.github.migangqui.spring.email.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/emails")
@EnableAsync
@SpringBootApplication
public class SpringEmailJavaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailJavaExampleApplication.class, args);
	}

	@Autowired
	private EmailSender emailSender;

	@PostMapping
	public SendEmailResult sendEmail(@RequestBody Email email) {
		return emailSender.send(email);
	}

	@PostMapping("/async")
	public SendEmailResult sendEmailAsync(@RequestBody Email email) throws ExecutionException, InterruptedException {
		return emailSender.sendAsync(email).get();
	}
}
