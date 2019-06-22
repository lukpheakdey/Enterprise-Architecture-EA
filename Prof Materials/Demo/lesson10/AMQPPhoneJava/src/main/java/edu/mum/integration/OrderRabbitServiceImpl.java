package edu.mum.integration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.mum.domain.Order;

public class OrderRabbitServiceImpl implements OrderRabbitService {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void publish(Order order) {
         rabbitTemplate.convertAndSend("order.create",order);
     }
}
