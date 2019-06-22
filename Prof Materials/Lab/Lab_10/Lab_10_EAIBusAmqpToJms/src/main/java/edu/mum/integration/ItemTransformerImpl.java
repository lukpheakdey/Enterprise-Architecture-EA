 
package edu.mum.integration;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import edu.mum.domain.RouteItem;
import edu.mum.domain.RouteItem.RouteItemType;
import edu.mum.domain.Item;

/**
 * Routes order based on order type.
 * 
 */
public class ItemTransformerImpl implements ItemTransformer {

     /**
     * Transform Item from AMQP to RouteItem for JMS
      */
	@Transformer(inputChannel="fromAmqpItem", outputChannel="processItem")
	public RouteItem transformItem(Item item) {
 
 		RouteItem routeItem = null;
 		if(item.getPrice() > 1500.0) {
 			routeItem = new RouteItem(item, RouteItemType.HIGH_PRICE);
 		} else {
 			routeItem = new RouteItem(item, RouteItemType.MODERATE_PRICE);
 		}
	    	 	
		return routeItem;
	}

}
