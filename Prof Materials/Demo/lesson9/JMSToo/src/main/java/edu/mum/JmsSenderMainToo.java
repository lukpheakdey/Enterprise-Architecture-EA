package edu.mum;

import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.domain.Product;
import edu.mum.sender.MessageSender;

public class JmsSenderMainToo {
    public static void main(String[] args) {
    	
 
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/spring/jms-sender-app-context.xml");
        context.refresh();
        
        MessageSender messageSender = context.getBean("messageSender", MessageSender.class);
      
        // Product to send...
 
    	Product product = new Product("Baton", "a Real Twirler", 19);
        messageSender.sendMessage(product);
        
        System.out.println("JMSToo Sent Baton to Queue");

    }
}
