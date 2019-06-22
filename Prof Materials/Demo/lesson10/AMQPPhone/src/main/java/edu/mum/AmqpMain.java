package edu.mum;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.amqp.OrderService;
import edu.mum.amqp.OrderServiceImpl;

public class AmqpMain {
	
    public static void main(String[] args) {
         ApplicationContext context = new GenericXmlApplicationContext("classpath:META-INF/spring/order-app-context.xml");
 
         RabbitTemplate topicTemplate =  context.getBean("topicTemplate",RabbitTemplate.class);
     	OrderService orderService = new OrderServiceImpl();
     	orderService.publish(topicTemplate);

     	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //   context.close();
    }
}
