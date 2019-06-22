/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.mum.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Component;

import edu.mum.builder.OrderBuilder;
import edu.mum.builder.OrderItemBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Order;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.RouteOrder;
import edu.mum.domain.RouteOrder.RouteOrderType;
import edu.mum.integration.OrderGateway;

 
@Component
public class Main {

	private final static String[] configFilesGatewayDemo = {
		"/spring/integration/common.xml",
 		"/spring/integration/orderGateway.xml",
		"/spring/integration/mailContext.xml",
		"/spring/integration/amqp-order-app-context.xml",
		"/spring/integration/jms-albia-app-context.xml",
		"/spring/integration/jms-warehouse-app-context.xml"
	};


	OrderGateway orderGateway;
	


	public static void main(String[] args) {

		final Scanner scanner = new Scanner(System.in);

//	    RouteOrderGateway orderGateway;


		System.out.println("\n========================================================="
				+ "\n                                                         "
				+ "\n    Welcome to the WAA 545 RouteOrder System!                 "
				+ "\n                                                         "
				+ "\n    For more information please visit:                   "
				+ "\n    REFER to the Slides & your Class NOTES!              "
				+ "\n                                                         "
				+ "\n=========================================================" );
 
 				System.out.println("    Loading Gateway Demo...");
				ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFilesGatewayDemo, Main.class);

			    applicationContext.getBean(Main.class).mainInternal(applicationContext);
	  }
	    private void mainInternal(ApplicationContext applicationContext) {
				
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

	 RouteOrder message = new RouteOrder(order, RouteOrderType.PICKUP);

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
	  message = new RouteOrder(order, RouteOrderType.DELIVERY);

			try {
				orderGateway.process(message);
			}
			catch ( MessageDeliveryException e)	
			{
				System.out.println("Bad message");
			}
		
		System.out.println();
		System.out.println("Good Work: Albia Location got a order/pick up notice & an Email was sent to Customer!");
		System.out.println("The Warehouse got an order!");
}

}
