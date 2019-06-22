package edu.mum.amqp;

import edu.mum.domain.Order;

public class DirectListener {

	// Listens on the "directQueue" associated with the "direct" exchange
	public void listen(Order order) {
			
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- Read AMQP Order for Product on Direct Queue: " + name);
	}
}
