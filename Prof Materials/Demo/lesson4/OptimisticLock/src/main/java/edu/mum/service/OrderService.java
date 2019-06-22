package edu.mum.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;

import edu.mum.domain.Order;
 
public interface OrderService {

	public void save(Order userCredentials);
  	
	public List<Order> findAll();

	public Order findOne(Long id, Map<String,Object> hints);

	public Order findByGraph(Long id);

	public Order update(Order order);

 
}
