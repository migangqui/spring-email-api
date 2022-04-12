package com.github.migangqui.spring.email.model;

import java.io.InputStream;
import java.util.Optional;

public record Email(String from, String to, String subject, String body,
					Optional<InputStream> file, Optional<String> filename) {

	public Email(String from, String to, String subject, String body) {
		this(from, to, subject, body, Optional.empty(), Optional.empty());
	}

	public Email(String from, String to, String subject, String body, InputStream file, String filename) {
		this(from, to, subject, body, Optional.ofNullable(file), Optional.ofNullable(filename));
	}

}