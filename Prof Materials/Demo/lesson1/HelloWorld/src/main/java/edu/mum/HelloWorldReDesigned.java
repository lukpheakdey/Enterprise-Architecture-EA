package edu.mum;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;
import edu.mum.component.impl.HelloWorldMessageSource;
import edu.mum.component.impl.StandardOutMessageDisplay;

public class HelloWorldReDesigned {
    public static void main(String[] args) {
        MessageDisplay messageDisplay = getMessageDisplay();
        MessageSource messageSource = getMessageSource();
        messageDisplay.setMessageSource(messageSource);
        messageDisplay.display();
    }
    
    private static MessageDisplay getMessageDisplay() {    	
    	return new StandardOutMessageDisplay();
    }

    private static MessageSource getMessageSource() {    	
    	return new HelloWorldMessageSource();
    }


}
