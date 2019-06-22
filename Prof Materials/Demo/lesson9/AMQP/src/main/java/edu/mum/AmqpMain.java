package edu.mum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.amqp.AnswerServiceImpl;
import edu.mum.amqp.DirectServiceImpl;
import edu.mum.amqp.OrderService;
import edu.mum.amqp.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class AmqpMain {
	
    public static void main(String[] args) {
         ApplicationContext context = new GenericXmlApplicationContext("classpath:META-INF/spring/order-app-context.xml");
 
         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         System.out.print("*************TO SEE STORE purchases in TEMPORARY QUEUE - AMQPClient needs to be running before HIT RETURN*********************   ");
         System.out.println();
         System.out.print("*************HIT RETURN To Send[Bicycle,Chandelier & Soccer Ball] on TOPIC Exchange*********************::   ");
         System.out.println();
         try {
			in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
                       
         RabbitTemplate topicTemplate =  context.getBean("topicTemplate",RabbitTemplate.class);
     	OrderService orderService = new OrderServiceImpl();   // Publish to Topic
     	orderService.publish(topicTemplate);

    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        
        System.out.print("*************HIT RETURN To Send[Kazoo & Water Balloon] to Direct Queue & Send[Ski & Skates] to Answer Queue on Direct Exchange*********************::   ");
        System.out.println();
        try {
			in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
    	// Publish to "direct" exchange on order.key == directQueue
        RabbitTemplate directTemplate =  context.getBean("directTemplate",RabbitTemplate.class);
    	OrderService directService = new DirectServiceImpl();
    	directService.publish(directTemplate);

     	// Publish to "direct" exchange on order.answer == answerQueue
       RabbitTemplate answerTemplate =  context.getBean("answerTemplate",RabbitTemplate.class);
    	OrderService answerService = new AnswerServiceImpl();
    	answerService.publish(answerTemplate);

    //   context.close();
    }
}
