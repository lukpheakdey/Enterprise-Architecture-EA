package edu.mum.amqp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;

public class AnswerServiceImpl implements OrderService {
    public void publish(RabbitTemplate rabbitTemplate) {
 
    	// Dummy up an order
    	// First need a Product:
    	Product product = new Product("Skates", "a nice glide", 2);
    	// Order 2 of them
    	OrderItem orderItem = new OrderItem(2, product);
    	// Make a list of the orderItems [ only 1]
    	List<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
 
        OrderPayment orderPayment = new OrderPayment();
        // Create order...
        Order order = new Order("123",orderItems,orderPayment);
        rabbitTemplate.convertAndSend(order);
         
    	// Dummy up a  SECOND order - simply change the product name
        order.getItems().get(0).getProduct().setName("Skis");
        rabbitTemplate.convertAndSend(order);
 
 
    }
}
