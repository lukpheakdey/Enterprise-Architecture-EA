package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;
import edu.mum.service.OrderService;
import edu.mum.service.ProductService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    
    OrderService orderService = (OrderService) ctx.getBean("orderServiceImpl");
    ProductService productService = (ProductService) ctx.getBean("productServiceImpl");
    
    Order order = new Order();
    order.setOrderNumber("52");
    
    orderService.save(order);
    
    order.setOrderNumber("102");
    Order mergedOrder = orderService.update(order);
	System.out.println("***** Integer version **************" );
 
//  	System.out.println("Persisted order object has version = " + order.getVersion() );
 	System.out.println("Merged Order - a copy of persisted order object has version = " + mergedOrder.getVersion() );
 	System.out.println("updating original Persisted order with version = " + order.getVersion() + " causes:  " );

    order.setOrderNumber("202");
    try {
    mergedOrder = orderService.update(order);
    }
    catch (ObjectOptimisticLockingFailureException exception) {
    	System.out.println("Optimistic Locking Failed Exception" );
    }

    // Database time stamp
  Product product = new Product();
  product.setName("Optimistic Product");
  productService.save(product);
   
	System.out.println("***** TimeStamp version **************" );
	System.out.println("Persisted product object has version = " + product.getVersion() );

	
	Product getProduct = productService.findOne(product.getId());
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	getProduct.setName("Optimistic Product Timestamp");
	getProduct = productService.update(getProduct);
		System.out.println("Updated product object has version = " + getProduct.getVersion() );
		System.out.println("TRY to Update [stale] persisted object ");
		 
	    try {
	        product = productService.update(product);
	        }
	        catch (ObjectOptimisticLockingFailureException exception) {
	        	System.out.println("Product Optimistic Locking Failed Exception" );
	        }

		
  }
  
  
  }