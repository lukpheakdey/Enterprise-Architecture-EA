 
package edu.mum.integration;

import edu.mum.domain.RouteOrder;
import edu.mum.domain.Order;

/**
 * Routes order based on order type.
 * 
 */

public interface OrderTransformer {

	public RouteOrder transformOrder(Order order);

}
