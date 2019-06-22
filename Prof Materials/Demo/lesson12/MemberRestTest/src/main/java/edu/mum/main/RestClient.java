package edu.mum.main;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.rest.RestHttpHeader;
import edu.mum.service.ProductService;

@Component
public class RestClient
{
	@Autowired
	RestHttpHeader remoteApi;

	@Autowired
	ProductService productService;
	

	public static void main(String[] args) {
		
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		
		applicationContext.getBean(RestClient.class).mainInternal(applicationContext);
	}
	
	private void mainInternal(ApplicationContext applicationContext) {
 
/*	  	   		Product product = productService.findOne(1);
	   	   		System.out.println();
	  	   		System.out.println("single Product " + product.getName()); 
	  	   		System.out.println(); 

*/
	List<Product> products = productService.findAll();
    for (Product productTemp : products) {
		System.out.println("Product " + productTemp.getName());
		for (Category category : productTemp.getCategories())
    		System.out.println("       Category " + category.getName());

    }
	System.out.println(); 
    
	}
}
