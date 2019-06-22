package edu.mum.amqp;

import edu.mum.domain.Order;

public class OrderListenerStore {

	public void listen(Order order) {
		
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- AMQPClient TOPIC Order for Product with key purchases.store ...: " + name);
	}
}
