package edu.mum.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Order;
import edu.mum.integration.OrderGateway;

public interface OrderService {
    public void publish(OrderGateway orderGateway);
}
