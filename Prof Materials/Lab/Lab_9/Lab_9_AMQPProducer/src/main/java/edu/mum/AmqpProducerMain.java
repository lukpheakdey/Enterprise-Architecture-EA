package edu.mum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.amqp.ItemService;
import edu.mum.amqp.ItemServiceImpl;

public class AmqpProducerMain {
	
    public static void main(String[] args) {
    	 ApplicationContext context = new GenericXmlApplicationContext("classpath:META-INF/spring/item-app-context.xml");
    	 RabbitTemplate directTemplate = context.getBean("directTemplate", RabbitTemplate.class);
    	 
    	 ItemService directService = new ItemServiceImpl();
    	 directService.publish(directTemplate);
    	 try {
    		 Thread.sleep(1000);
    	 } catch(InterruptedException e) {
    		 e.printStackTrace();
    	 }
    	 
    	
 
    }
}
