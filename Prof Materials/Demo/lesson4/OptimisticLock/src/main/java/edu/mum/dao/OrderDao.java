package edu.mum.dao;

import edu.mum.domain.Order;

public interface OrderDao extends GenericDao<Order> {
  
	public Order findByGraph(Long id);

 }
