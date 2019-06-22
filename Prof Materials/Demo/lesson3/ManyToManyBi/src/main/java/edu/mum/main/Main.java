package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    ProductService productService = (ProductService) ctx.getBean("productServiceImpl");
    CategoryService categoryService = (CategoryService) ctx.getBean("categoryServiceImpl");


	 // Categories...
	 Category category =  new CategoryBuilder()
	 	      	.withName("Sports")
	 	    	.withDescription("Athletic")
 	 	    	.build();     	      
 
		Category category2 =  new CategoryBuilder()
	 	      	.withName("Toys")
	 	    	.withDescription("Play Things")
	 	 	    	.build();     	      

		Category category3 =  new CategoryBuilder()
	 	      	.withName("Holiday Gifts")
	 	    	.withDescription("Surprises!!")
	 	 	    	.build();     	      

	// Products
	 Product product =  new ProductBuilder()
	 	      	.withName("Sled")
	 	    	.withDescription("Winter time fun")
	 	    	.withPrice(22.0F)
	 	    	.withStatus(ProductionStatus.PRODUCTION)
	 	    	.withCategory(category)
	 	    	.withCategory(category2)
	 	    	.build();     	      

	 //Product has "convenience method...set both sides  - used in ProductBuilder
	 productService.save(product);

	 Product product2 =  new ProductBuilder()
		 	      	.withName("Skates")
		 	    	.withDescription("Winter time gliding")
		 	    	.withPrice(44.0F)
		 	    	.withStatus(ProductionStatus.PRODUCTION)
		 	    	.withCategory(category)
		 	    	.withCategory(category2)
		 	    	.withCategory(category3)
		 	    	.build();     	      

	 // Need to merge .. to handle detached categories....
	 // Product has "convenience method...set both sides   - used in ProductBuilder
	    productService.update(product2);

	    
	    
   product =  productService.findByProductName(product.getName());
    
   System.out.println();
   System.out.println("---------Product->Categories--------------");
    System.out.println("Product Name : " + product.getName());

	for (Category categorie : product.getCategories()) {
	System.out.println("Category Name : " + categorie.getName());
	}


 		   product2 =  productService.findByProductName(product2.getName());
		    
		    System.out.println("---------Product->Categories--------------");
		    System.out.println("Product TWO Name : " + product2.getName());

			for (Category categorie : product2.getCategories()) {
			System.out.println("Category Name : " + categorie.getName());
			}


			   category =  categoryService.findByCategoryName(category.getName());
			    
 

			    System.out.println("--------Category-> Products--------------");

			    System.out.println("Category Name : " + category.getName());

				for (Product producte : category.getProducts()) {
				System.out.println("Product Name : " + producte.getName());
				}


  }  
  }