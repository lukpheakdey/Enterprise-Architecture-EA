  

package edu.mum.domain;


import java.io.Serializable;

public class RouteOrder  implements Serializable {

    public enum RouteOrderType { DELIVERY, PICKUP }

    Order order;
    private final RouteOrderType orderType;

    /**
     * Constructor
     */
    public RouteOrder(Order order, RouteOrderType orderType) {
         this.orderType = orderType;
        this.order  = order;
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

 }
