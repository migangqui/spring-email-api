package com.github.migangqui.spring.email.bean;

import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Email {
	
	private String from;
	private String to;
	private String subject;
	private String body;
	private InputStream file; 
	private String filename;
	
}