package edu.mum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.domain.Product;
import edu.mum.sender.MessageSender;
 
public class JmsMain {
    public static void main(String[] args) throws IOException {
    	
 
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load(
        		"classpath:META-INF/spring/jms-sender-app-context.xml",
        		"classpath:META-INF/spring/jms-init-context.xml",
                "classpath:META-INF/spring/jms-listener-app-context.xml");
        context.refresh();
        
        String selector = null;
        String value = null;
        
      	// PTP sender
        MessageSender messageSender = context.getBean("messageSender", MessageSender.class);
        // Pub/Sub Sender
        MessageSender topicMessageSender = context.getBean("topicMessageSender", MessageSender.class);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.print("*************HIT RETURN To Send PTP*********************::   ");
        System.out.println();
        in.readLine();
         
    	Product product = new Product("Kazoo", "a Hummer", 2);
        messageSender.sendMessage(product,selector,value);
 
        
         System.out.println();
        System.out.print("*************HIT RETURN To Send PUB-SUB*********************::  ");
        System.out.println();
        in.readLine();
         
    	product = new Product("Bicycle", "Two Wheeler", 79);
        topicMessageSender.sendMessage(product,selector,value);
 
        // the JmsTopicToo project listener filters on the  "online" selector
    	product = new Product("Chandelier", "Sunny Day", 880);
    	selector = "online";
    	value="true";
        topicMessageSender.sendMessage(product,selector,value);
 
    }
}
