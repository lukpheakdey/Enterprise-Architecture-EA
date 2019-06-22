package edu.mum;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;
import edu.mum.configuration.MessageConfiguration;

public class HelloWorldReDesignedWithConfiguration {
    public static void main(String[] args) {
    	
    	MessageConfiguration messageConfiguration = MessageConfiguration.getInstance();
    	
        MessageDisplay messageDisplay = messageConfiguration.getMessageDisplay();
        messageDisplay.display();
    }
}
