 
  
package edu.mum.integration;

import org.springframework.integration.annotation.Gateway;

import edu.mum.domain.RouteOrder;
import edu.mum.domain.Order;

/**
 * An entry point for the Bus Flow for local input.
 * 
 */
public interface OrderGateway {

    /**
     * Process a book order.
     */
	@Gateway(requestChannel="processOrder")
	public void process(RouteOrder order);

}
