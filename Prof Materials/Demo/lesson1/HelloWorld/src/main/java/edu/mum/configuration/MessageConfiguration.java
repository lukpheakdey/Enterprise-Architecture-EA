package edu.mum.configuration;

import java.io.InputStream;
import java.util.Properties;

 import java.lang.reflect.Method;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;
import edu.mum.component.factory.DisplayFactory;
import edu.mum.component.factory.SourceFactory;

/*
 * Implemented as a Singleton...
 */
public class MessageConfiguration {
    // Is invoked only once [since final] ... happens when we call getInstance() below
	private static final MessageConfiguration instance = new MessageConfiguration();

    private Properties properties;
    private MessageDisplay display;
    private MessageSource source;
 
    // No other classes can create an instance...
    private MessageConfiguration() {
    	properties = new Properties();

        String fileName = "HelloWorld.properties";
        try {
        	
    		// Use ClassLoader to find resource...
        	InputStream  input = this.getClass().getClassLoader().getResourceAsStream("edu/mum/resources/" + fileName);
	    		if(input==null){
    	            System.out.println("Unable to find " + fileName);
    		    return ;
    		}
 
    		//load in properties file data 
    		properties.load(input);
            String displayClass = properties.getProperty("display.class");
            String sourceClass = properties.getProperty("source.class");

            display = DisplayFactory.getInstance(displayClass);
            source = SourceFactory.getInstance(sourceClass);
  
             // "Manually" set source in display...
            display.setMessageSource(source);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Access Singleton instance
    public static MessageConfiguration getInstance() {
        return instance;
    }

    public MessageDisplay getMessageDisplay() {
        return display;
    }

    public MessageSource getMessageSource() {
        return source;
    }
}

