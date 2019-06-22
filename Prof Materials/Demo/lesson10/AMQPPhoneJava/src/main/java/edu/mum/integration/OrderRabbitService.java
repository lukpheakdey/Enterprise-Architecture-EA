package edu.mum.integration;

import edu.mum.domain.Order;

public interface OrderRabbitService  {

	public void publish(Order order) ;
 

}
