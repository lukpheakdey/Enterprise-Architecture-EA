package edu.mum.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.aspect.annotation.Logging;
import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;

@Service
@Transactional 
public class OrderServiceImpl implements edu.mum.service.OrderService {
	
 	@Autowired
	private OrderDao orderDao;


 	@Logging
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
	
	@Logging
	public Order update(Order order) {
		this.updateAdjunct();
	    System.out.println("**********         UpdateAdjunct called from within Same Class - not Logged!       **********     ");
		return orderDao.update(order);
	}

	// Never gets logged when called from update - 
	// BECAUSE of  Method level proxy "gotcha"
	
	@Logging
	public void updateAdjunct() {
		return ;
	}

	
 
}
