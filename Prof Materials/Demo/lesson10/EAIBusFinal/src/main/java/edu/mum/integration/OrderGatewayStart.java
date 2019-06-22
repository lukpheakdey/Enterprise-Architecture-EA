package edu.mum.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Component;

import edu.mum.builder.OrderBuilder;
import edu.mum.builder.OrderItemBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Order;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.RouteOrder;
import edu.mum.domain.RouteOrder.RouteOrderType;

/*
 *  Based on @EventListener - this activates the local OrderGateway after startup..
 *  The remainder of the orders come remotely -- AMQP, HTTP...
 */
@Component
public class OrderGatewayStart {

	@Autowired
	  private ApplicationContext applicationContext;
 
	OrderGateway orderGateway;

	@EventListener
	public void orderGatewayStartup(ContextRefreshedEvent event) {
		
		System.out.println("\n========================================================="
			+ "\n                                                         "
			+ "\n    Welcome to the WAA 545 RouteOrder System!                 "
			+ "\n                                                         "
			+ "\n    For more information please visit:                   "
			+ "\n    REFER to the Slides & your Class NOTES!              "
			+ "\n                                                         "
			+ "\n=========================================================" );
 			
    	String store = "Albia";

    	Order order = new OrderBuilder()
    			.withOrderNumber("535")
    			.withOrderPayment(new OrderPayment())
    			.withOrderItem( new OrderItemBuilder()
    					.withQuantity(2)
    					.withProduct(new ProductBuilder()
    							.withName("Stranger in a Strange Land")
    							.withDescription("Pretty Strange")
    							.withPrice(14F)
    							.build())
    					.build())
     		   .build();

 RouteOrder message = new RouteOrder(order, RouteOrderType.PICKUP, store);

    orderGateway = (OrderGateway) applicationContext.getBean("order");
		
    System.out.println("  Thank your for your RouteOrder! \n");
	
		try {
			orderGateway.process(message);
		}
		catch ( MessageDeliveryException e)	
		{
			System.out.println("Bad message");
		}

		store = "Batavia";
    	order = new OrderBuilder()
    			.withOrderNumber("536")
    			.withOrderPayment(new OrderPayment())
    			.withOrderItem( new OrderItemBuilder()
    					.withQuantity(2)
    					.withProduct(new ProductBuilder()
    							.withName("The Blue Nile")
    							.withDescription("Long & Winding")
    							.withPrice(14F)
    							.build())
    					.build())
     		   .build();

     message = new RouteOrder(order, RouteOrderType.PICKUP, store);

    orderGateway = (OrderGateway) applicationContext.getBean("order");
		
    System.out.println("  Thank your for your RouteOrder! \n");
	
		try {
			orderGateway.process(message);
		}
		catch ( MessageDeliveryException e)	
		{
			System.out.println("Bad message");
		}

		order = new OrderBuilder()
    			.withOrderNumber("538")
    			.withOrderPayment(new OrderPayment())
    			.withOrderItem( new OrderItemBuilder()
    					.withQuantity(1)
    					.withProduct(new ProductBuilder()
    							.withName("The Life of Riley")
    							.withDescription("Easy Street")
    							.withPrice(11F)
    							.build())
    					.build())
     		   .build();
  store = "";
  message = new RouteOrder(order, RouteOrderType.DELIVERY,store);

		try {
			orderGateway.process(message);
		}
		catch ( MessageDeliveryException e)	
		{
			System.out.println("Bad message");
		}
		
	System.out.println();
		System.out.println("The Warehouse got orders!");
	System.out.println();

    	}
}
	
	