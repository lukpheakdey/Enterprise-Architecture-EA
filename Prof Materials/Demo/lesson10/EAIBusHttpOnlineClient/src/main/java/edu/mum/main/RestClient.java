package edu.mum.main;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.builder.OrderBuilder;
import edu.mum.builder.OrderItemBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Order;
import edu.mum.domain.OrderPayment;
import edu.mum.rest.RestHttpHeader;
import edu.mum.service.OrderService;

@Component
public class RestClient
{
	@Autowired
	RestHttpHeader remoteApi;

	@Autowired
	OrderService orderService;
	

	public static void main(String[] args)
	{
 	    final ApplicationContext applicationContext =
	            new ClassPathXmlApplicationContext("context/applicationContext.xml");
 	 
		applicationContext.getBean(RestClient.class).mainInternal(applicationContext);
	      }
	
	        private void mainInternal(ApplicationContext applicationContext) {
 
	        	Order order = new OrderBuilder()
	        			.withOrderNumber("765")
	        			.withOrderPayment(new OrderPayment())
	        			.withOrderItem( new OrderItemBuilder()
	        					.withQuantity(3)
	        					.withProduct(new ProductBuilder()
	        							.withName("Wool Sweater")
	        							.withDescription("Ewe like")
	        							.withPrice(134F)
	        							.build())
	        					.build())
	         		   .build();

	        	
  	   		
  
  		  orderService.addOrder(order);
  		  
  
	}
}
