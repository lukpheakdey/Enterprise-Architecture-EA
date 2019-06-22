package edu.mum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.component.MessageDisplay;

@Component
public class HelloWorld {
 
	  @Autowired
	  MessageDisplay messageDisplay;


	public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        applicationContext.getBean(HelloWorld.class).mainInternal(applicationContext);
    }
    
      private void mainInternal(ApplicationContext applicationContext) {
   
        messageDisplay.display();
    }
}
