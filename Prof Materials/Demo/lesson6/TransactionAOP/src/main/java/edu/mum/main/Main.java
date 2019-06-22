package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import edu.mum.domain.Order;
import edu.mum.service.OrderService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    
    OrderService orderService = (OrderService) ctx.getBean("orderServiceImpl");
    
    Order order = new Order();
    order.setOrderNumber("52");
    
    orderService.save(order);
    
    order.setOrderNumber("102");
    Order mergedOrder = orderService.update(order);
    
   
  }


  
  }