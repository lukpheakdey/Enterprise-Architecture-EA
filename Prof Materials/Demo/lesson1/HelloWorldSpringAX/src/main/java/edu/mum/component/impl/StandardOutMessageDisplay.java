package edu.mum.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;

@Component
public class StandardOutMessageDisplay implements MessageDisplay {

	@Autowired
	private MessageSource messageSource;

    
    public void display() {
        if (messageSource == null) {
            throw new RuntimeException(
                "You must set the property messageSource of class:"
                + StandardOutMessageDisplay.class.getName());
        }

        System.out.println(messageSource.getMessage());
    }

    
    public void setMessageSource(MessageSource source) {
        this.messageSource = source;
    }

   
    public MessageSource getMessageSource() {
        return this.messageSource;
    }
}
