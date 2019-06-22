 
package edu.mum.integration;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import edu.mum.domain.RouteOrder;
import edu.mum.domain.RouteOrder.RouteOrderType;
import edu.mum.domain.Order;

/**
 * Routes order based on order type.
 * 
 */
public class OrderTransformerImpl implements OrderTransformer {

     /**
     * Transform Order from AMQP to RouteOrder for JMS
      */
	@Transformer(inputChannel="fromAmqpOrder", outputChannel="processOrder")
	public RouteOrder transformOrder(Order order) {
 
		String name = order.getItems().get(0).getProduct().getName();
		int quantity = order.getItems().get(0).getQuantity();
		int orderNumber = Integer.parseInt(order.getOrderNumber());
	    
		RouteOrder routeOrder = new RouteOrder(name, quantity, RouteOrderType.DELIVERY, orderNumber);
	    	 	
		return routeOrder;
	}

}
