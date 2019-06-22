package edu.mum.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;

@Service
@Transactional 
public class OrderServiceImpl implements edu.mum.service.OrderService {
	
 	@Autowired
	private OrderDao orderDao;

 	
     public void save( Order userCredentials) {  		
    	 orderDao.save(userCredentials);
 	}
  	
 	public Order findOne(Long id, Map<String,Object> hints) {
 		return orderDao.findOne(id, (Map<String,Object>)hints);
 	}

  	public Order findByGraph(Long id) {
  		
  		return orderDao.findByGraph(id);
  	}
  	
	public List<Order> findAll() {
		return (List<Order>)orderDao.findAll();
	}
	
	public Order update(Order order) {
		return orderDao.update(order);
	}

 
}
