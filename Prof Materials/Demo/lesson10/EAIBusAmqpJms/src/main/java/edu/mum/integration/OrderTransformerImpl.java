 
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
 
 		RouteOrder routeOrder = new RouteOrder(order, RouteOrderType.DELIVERY);
	    	 	
		return routeOrder;
	}

}
