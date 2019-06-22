package edu.mum.builder;

import java.util.List;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;

public class OrderBuilder {

     private Order order;
    
 	public OrderBuilder() {
		this.order = new Order();
	}

 	
    public OrderBuilder withOrderPayment(OrderPayment orderPayment) {
        this.order.setPayment(orderPayment);
        return this;
    }

    public OrderBuilder withOrderItems(List<OrderItem> orderItems) {
        this.order.setItems(orderItems);
          return this;
     }

    public OrderBuilder withOrderItem(OrderItem orderItem) {
        this.order.getItems().add(orderItem);
          return this;
     }

    public OrderBuilder withOrderNumber(String orderNumber) {
        this.order.setOrderNumber(orderNumber);
        return this;
    }

    public OrderBuilder withId(Long id) {
        this.order.setId(id);
        return this;
    }

    public Order build() {
        return order;
    }

	
}
