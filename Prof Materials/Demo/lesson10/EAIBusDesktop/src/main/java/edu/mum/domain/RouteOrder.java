  

package edu.mum.domain;


import java.io.Serializable;

public class RouteOrder  implements Serializable {

    public enum RouteOrderType { DELIVERY, PICKUP }

    private final String name;
    private final int quantity;
    private final int orderNumber;
    private final RouteOrderType orderType;

    /**
     * Constructor
     */
    public RouteOrder(String name, int quantity, RouteOrderType orderType, int orderNumber) {
        this.name = name;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderNumber = orderNumber;
    }

    /**
     * Gets name.
     */
 
    public String getName() {
        return name;
    }

    /**
     * Gets quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets order type.
     */
    public RouteOrderType getRouteOrderType() {
        return orderType;
    }

	public int getOrderNumber() {
		return orderNumber;
	}

	public RouteOrderType getOrderType() {
		return orderType;
	}

}
