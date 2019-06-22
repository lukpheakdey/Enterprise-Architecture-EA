 
package edu.mum.integration;

import edu.mum.domain.RouteItem;
import edu.mum.domain.Item;

/**
 * Routes order based on order type.
 * 
 */

public interface ItemTransformer {

	public RouteItem transformItem(Item order);

}
