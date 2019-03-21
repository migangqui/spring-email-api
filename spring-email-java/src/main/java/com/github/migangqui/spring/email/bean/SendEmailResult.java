package com.github.migangqui.spring.email.bean;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class SendEmailResult {
	private int status;
	private String cause;
	private Exception exception;
}