package edu.mum.main;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;

@Component
public class ProductProcess {

 	@Autowired
	ProductService productService;
	
 	// Called from ProductBatch - when job is completed
 	// Prints out a summary of processed Products
 	public void start() throws IOException {
		
 		  System.out.println("******* List of Products AND Status");
	
		 List<Product> products = productService.findAll();
		 for (Product product : products) {
			 System.out.println();
			 System.out.print("Product name: " + product.getName() 
			        + "  Status: " + product.getStatus());
		 }
		 System.out.println();
		 
	}
}
