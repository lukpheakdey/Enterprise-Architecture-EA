 
  
package edu.mum.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import edu.mum.domain.Order;

/**
 * The entry point for the Bus Flow.
 * 
 */
@MessagingGateway
public interface OrderGateway {

   
	@Gateway(requestChannel="toAmqpOrder")
	public void process(Order order);

}
