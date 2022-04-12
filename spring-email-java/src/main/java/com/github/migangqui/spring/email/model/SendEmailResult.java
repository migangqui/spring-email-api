package com.github.migangqui.spring.email.model;

public record SendEmailResult(Integer status, String cause, Exception exception) {

	public SendEmailResult(final Integer status) {
		this(status, null, null);
	}

}