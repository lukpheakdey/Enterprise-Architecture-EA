package edu.mum.component.impl;

import org.springframework.stereotype.Component;

import edu.mum.component.MessageSource;

@Component
public class HelloWorldMessageSource implements MessageSource {
 
	public String getMessage() {
		  return "Hello World!";	}
 }
