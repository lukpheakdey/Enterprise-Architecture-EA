package edu.mum.domain;

import java.io.Serializable;
import java.util.Set;

public class OrderPayment implements Serializable {

	   public OrderPayment() {}
	   public OrderPayment (Integer paymentNumber, Integer amount, String paymentType ) {
		   this.paymentNumber = paymentNumber;
		   this.amount = amount;
		   this.paymentType = paymentType;
	   }

	
 	   private Long id = null;
 	   private int version = 0;

 
	   private Integer paymentNumber;

 
	   private String paymentType;
 
	   private Integer amount;

 
	   private Order order;

	   
	   
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

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
