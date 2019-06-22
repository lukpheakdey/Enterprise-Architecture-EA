package edu.mum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import edu.mum.component.MessageDisplay;

@SpringBootApplication
public class HelloWorld {

	  @Autowired
	  MessageDisplay messageDisplay;


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(HelloWorld.class, args);
	     applicationContext.getBean(HelloWorld.class).mainInternal(applicationContext);
	 }
	 
	   private void mainInternal(ApplicationContext applicationContext) {
	        messageDisplay.display();
	    }
}
