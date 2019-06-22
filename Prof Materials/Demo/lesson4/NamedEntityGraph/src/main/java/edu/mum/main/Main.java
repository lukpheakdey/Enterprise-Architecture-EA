package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;
import edu.mum.service.OrderService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    
    OrderService orderService = (OrderService) ctx.getBean("orderServiceImpl");

    Order order = new Order();
    order.setOrderNumber("52");

    OrderPayment orderPayment = new OrderPayment();
    orderPayment.setPaymentNumber(1);
    order.addOrderPayment(orderPayment);
    
    OrderPayment orderPayment2 = new OrderPayment();
    orderPayment2.setPaymentNumber(2);
    order.addOrderPayment(orderPayment2);
    
    OrderPayment orderPayment3 = new OrderPayment();
    orderPayment3.setPaymentNumber(3);
    order.addOrderPayment(orderPayment3);
    
 
    Product product = new Product();
    product.setName("Bike");

    OrderItem orderItem = new OrderItem();
    orderItem.setQuantity(22);   
    orderItem.setProduct(product); 
    order.addOrderItem(orderItem);
    
    Product product2 = new Product();
    product2.setName("Chair");

    OrderItem orderItem2 = new OrderItem();
    orderItem2.setQuantity(222);   
    orderItem2.setProduct(product2); 
    order.addOrderItem(orderItem2);
    
    Product product3 = new Product();
    product3.setName("Car");

    OrderItem orderItem3 = new OrderItem();
    orderItem3.setQuantity(12);   
    orderItem3.setProduct(product3); 
    order.addOrderItem(orderItem3);

    orderService.save(order);
    
    // Find by Named graph - see OrderDaoImpl
    order = orderService.findByGraph(order.getId());
    
    System.out.println();
    System.out.println("Order Number : " + order.getOrderNumber() );
    System.out.println();
    System.out.println("   Payments   ");

    for (OrderPayment OrderPayment : order.getPayments()) 
        System.out.println("       Payment Number : " + OrderPayment.getPaymentNumber() );
 
    System.out.println();
    System.out.println("   Orders   ");
   for (OrderItem OrderItem : order.getItems()) {
       System.out.println("         Item Quantity: " + OrderItem.getQuantity() );
            System.out.println("              Product : " + OrderItem.getProduct().getName() );
    
   }

 
    


  }
  
  
  }