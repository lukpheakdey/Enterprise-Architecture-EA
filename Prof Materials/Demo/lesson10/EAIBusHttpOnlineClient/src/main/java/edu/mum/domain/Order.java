package edu.mum.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

  
public class Order implements Serializable{
 	   private Long id = null;
 	   private int version = 0;

 	   private String orderNumber;

 	   private List<OrderItem> items = new ArrayList<OrderItem>();

	   private OrderPayment payment = new OrderPayment();

 	   public Order() {}
 	   public Order (String orderNumber, List<OrderItem> items,OrderPayment payment ) {
 		   this.orderNumber = orderNumber;
 		   this.items = items;
 		   this.payment = payment;
 	   }
 	   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public OrderPayment getPayment() {
		return payment;
	}

	public void setPayment(OrderPayment payments) {
		this.payment= payments;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.items.add(orderItem);
		orderItem.setOrder(this);
	}

 
}
