package edu.mum.amqp;

import edu.mum.builder.OrderBuilder;
import edu.mum.builder.OrderItemBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Order;
import edu.mum.domain.OrderPayment;
import edu.mum.integration.OrderGateway;

public class OrderServiceImpl implements OrderService {
 

	// Uses Spring Integration "Gateway" to put message on channel 
	public void publish(OrderGateway orderGateway) {
 
 
        // Create order...
        
    	Order order = new OrderBuilder()
    			.withOrderNumber("123")
    			.withOrderPayment(new OrderPayment())
    			.withOrderItem( new OrderItemBuilder()
    					.withQuantity(2)
    					.withProduct(new ProductBuilder()
    							.withName("Leather Shoes")
    							.withDescription("DELUXE")
    							.withPrice(79F)
    							.build())
    					.build())
     		   .build();
    	
    	orderGateway.process(order);
    	
    }
}
