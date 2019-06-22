 
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
public class OrderRouter {

    final Logger logger = LoggerFactory.getLogger(OrderRouter.class);
    
    /**
     * Process order.  Routes based on whether or 
     * not the order is a delivery or pickup.
     */
	@Router(inputChannel="processOrder")
	public String processOrder(RouteOrder order) {
	    String destination = null;
	    
  	    switch (order.getRouteOrderType()) {
	        case DELIVERY:
	        	destination = "deliveryOrder";
	            break;
            case PICKUP:
            	destination = "pickupProcess";
                break;	            
	    }
	
		return destination;
	}

}
