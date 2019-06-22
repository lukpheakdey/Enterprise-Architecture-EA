package edu.mum.component.impl;

import org.springframework.stereotype.Component;

import edu.mum.component.MessageOrigin;

@Component
public class HelloWorldMessageOrigin implements MessageOrigin {
 
	public String getMessage() {
		  return "Hello World!";	}
 }
