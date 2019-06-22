 
package edu.mum.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.domain.RouteOrder;
import edu.mum.domain.Order;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Routes order based on order type.
 * 
 */
@MessageEndpoint
public class StoreRouter {

  	@Router(inputChannel="pickupOrder")
	public String determineStore(RouteOrder order) {
	    String destination = null;
	    
	    if (order.getStore().equals("Albia"))
	    	destination = "albiaPickup";
	    else destination = "bataviaPickup";
 	
		return destination;
	}

}
