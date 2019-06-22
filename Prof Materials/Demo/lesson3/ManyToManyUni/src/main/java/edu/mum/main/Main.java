package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.CategoryService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

     CategoryService categoryService = (CategoryService) ctx.getBean("categoryServiceImpl");

	 Product product =  new ProductBuilder()
 	      	.withName("Sled")
 	    	.withDescription("Winter time fun")
 	    	.withPrice(22.0F)
 	    	.withStatus(ProductionStatus.PRODUCTION)
 	    	.build();     	      

  // Second product
    
	 Product product2 =  new ProductBuilder()
	 	      	.withName("Skates")
	 	    	.withDescription("Winter time gliding")
	 	    	.withPrice(44.0F)
	 	    	.withStatus(ProductionStatus.PRODUCTION)
	 	    	.build();     	      
 
	 // Categories...
 	 Category category =  new CategoryBuilder()
	 	      	.withName("Sports")
	 	    	.withDescription("Athletic")
	 	    	.withProduct(product)
	 	    	.withProduct(product2)
 	 	    	.build();     	      
	    categoryService.save(category);

		category =  new CategoryBuilder()
		 	      	.withName("Toys")
		 	    	.withDescription("Play Things")
		 	    	.withProduct(product)
		 	    	.withProduct(product2)
	 	 	    	.build();     	      

		// Need to merge detached objects - will also persist parent
	    categoryService.update(category);
 

     for (Category categoree : categoryService.findAll()) {
	
	    System.out.println("--------Category -> Products--------------");

	    System.out.println("Category Name : " + categoree.getName());

		for (Product producte : categoree.getProducts()) {
		System.out.println("Product Name : " + producte.getName());
		}

  }
 
			//-------------- ADD ANOTHER product 
			

	 product =  new ProductBuilder()
	 	      	.withName("Skis")
	 	    	.withDescription("Two for One")
	 	    	.withPrice(144.0F)
	 	    	.withStatus(ProductionStatus.PRODUCTION)
	 	    	.build();     	      

 		    category.addProduct(product);
		    categoryService.update(category);

		    System.out.println("--------Category -> ADDITIONAL Products--------------");

		    System.out.println("Category Name : " + category.getName());

			for (Product producte : category.getProducts()) {
			System.out.println("Product Name : " + producte.getName());
			}


			
  }  
  }