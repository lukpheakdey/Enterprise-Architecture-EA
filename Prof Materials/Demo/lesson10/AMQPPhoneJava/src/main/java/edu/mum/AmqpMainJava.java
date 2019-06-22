package edu.mum;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.mum.amqp.OrderService;
import edu.mum.amqp.OrderServiceImpl;
import edu.mum.integration.OrderGateway;

public class AmqpMainJava {
	
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AmqpConfiguration.class);

        OrderGateway orderGateway =  context.getBean(OrderGateway.class);
        
        // Put order into Spring Integration through OrderGateway 
        OrderService orderService = new OrderServiceImpl();
     	orderService.publish(orderGateway);
     	
     	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //   context.close();
    }
}
