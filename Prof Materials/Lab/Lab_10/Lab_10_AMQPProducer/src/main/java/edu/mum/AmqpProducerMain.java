package edu.mum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.amqp.ItemService;
import edu.mum.amqp.ItemServiceImpl;

public class AmqpProducerMain {
	
    public static void main(String[] args) {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("************* Run the consumer (AMQPConsumer) before running this producer (AMQPProducer) *************");
        System.out.println();
        System.out.print("************* Hit 'return' to send Items [MacBookPro,Tesla, AppleWatch & iPhone] using direct exchange *************");
        System.out.println();
        try {
			in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
        ApplicationContext context = new GenericXmlApplicationContext("classpath:META-INF/spring/item-app-context.xml");

    	// publish to "direct" exchange on direct.item == directQueue
        RabbitTemplate directTemplate =  context.getBean("directTemplate",RabbitTemplate.class);
    	ItemService directItemService = new ItemServiceImpl();
    	directItemService.publish(directTemplate);
    }
}
