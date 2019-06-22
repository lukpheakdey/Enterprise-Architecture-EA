package edu.mum;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JmsSubscriberMain {
    public static void main(String[] args) {
    	
 
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/jms-listener-app-context.xml");
        ctx.refresh();
        
     }
}
