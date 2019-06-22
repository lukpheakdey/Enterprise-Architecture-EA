package edu.mum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.component.MessageDisplay;

public class HelloWorld {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        MessageDisplay messageDisplay = applicationContext.getBean("display", MessageDisplay.class);
        messageDisplay.display();
    }
}
