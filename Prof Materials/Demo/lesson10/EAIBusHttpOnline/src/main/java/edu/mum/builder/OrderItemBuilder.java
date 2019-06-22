package edu.mum.builder;

import edu.mum.domain.OrderItem;
import edu.mum.domain.Product;

public class OrderItemBuilder {

     private OrderItem orderItem;
    
 	public OrderItemBuilder() {
		this.orderItem = new OrderItem();
	}

 	
    public OrderItemBuilder withProduct(Product product) {
        this.orderItem.setProduct(product);
        return this;
    }
 

    public OrderItemBuilder withQuantity(Integer quantity) {
        this.orderItem.setQuantity(quantity);
        return this;
    }

    public OrderItemBuilder withId(Long id) {
        this.orderItem.setId(id);
        return this;
    }

    public OrderItem build() {
        return orderItem;
    }

	
}
