  

package edu.mum.domain;


import java.io.Serializable;

public class RouteOrder  implements Serializable {

    public enum RouteOrderType { DELIVERY, PICKUP }

    private final String store;

    Order order;
    private final RouteOrderType orderType;

    /**
     * Constructor
     */
    public RouteOrder(Order order, RouteOrderType orderType, String store) {
         this.orderType = orderType;
        this.order  = order;
        this.store = store;
    }

 
    /**
     * Gets order type.
     */
    public RouteOrderType getRouteOrderType() {
        return orderType;
    }

	public Order getOrder() {
		return order;
	}

	public String getStore() {
		return store;
	}

 }
