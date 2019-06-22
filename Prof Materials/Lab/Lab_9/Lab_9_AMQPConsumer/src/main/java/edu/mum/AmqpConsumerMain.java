package edu.mum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class AmqpConsumerMain {
	
    public static void main(String[] args) {
    	ApplicationContext context = new GenericXmlApplicationContext("classpath:META-INF/spring/item-app-context.xml");
    	
    	try {
    		Thread.sleep(1000);
    	} catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}
