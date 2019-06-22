package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Order;
import edu.mum.rest.service.OrderRestService;
import edu.mum.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
 	@Autowired
 	private OrderRestService orderRestService;

  	public void addOrder(Order order) {
 		orderRestService.save(order);
	}
	
 
}
