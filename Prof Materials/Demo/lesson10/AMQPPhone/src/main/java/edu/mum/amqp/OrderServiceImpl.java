package edu.mum.amqp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;

public class OrderServiceImpl implements OrderService {
    public void publish(RabbitTemplate rabbitTemplate) {
 
    	// Dummy up an order
    	// First need a Product:
    	Product product = new Product("Leather Shoes", "DELUXE", 79);
    	// Order 2 of them
    	OrderItem orderItem = new OrderItem(2, product);
    	// Make a list of the orderItems [ only 1]
    	List<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
 
        OrderPayment orderPayment = new OrderPayment();
        // Create order...
        Order order = new Order("123",orderItems,orderPayment);
        rabbitTemplate.convertAndSend("purchases.phone",order);
         
    }
}
