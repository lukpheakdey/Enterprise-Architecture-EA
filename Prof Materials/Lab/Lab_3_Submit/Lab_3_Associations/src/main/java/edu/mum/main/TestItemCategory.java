package edu.mum.main;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ItemBuilder;
import edu.mum.domain.Category;
import edu.mum.domain.Item;
import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;

@Component
public class TestItemCategory {

	@Autowired
	ItemService itemService;
	
	@Autowired
	CategoryService categoryService;
	

	public void testItemCategory() {

		 Category category =  new CategoryBuilder()
		 	      	.withName("Sports")
	 	 	    	.build();     	      
	 
			Category category2 =  new CategoryBuilder()
		 	      	.withName("Toys")
 	 	 	    	.build();     	      

			Category category3 =  new CategoryBuilder()
		 	      	.withName("Holiday Gifts")
 	 	 	    	.build();     	      
	
		 Item item =  new ItemBuilder()
		 	      	.withName("Sled")
		 	    	.withDescription("Winter time fun")
		 	    	.withInitialPrice(new BigDecimal(22.0))
 		 	    	.withCategory(category)
		 	    	.withCategory(category2)
		 	    	.build();     	      

 	    itemService.save(item);
	    
	// Second item
	    
	    item =  new ItemBuilder()
	 	      	.withName("Skates")
	 	    	.withDescription("Winter time gliding")
	 	    	.withInitialPrice(new BigDecimal(44.0))
		 	    .withCategory(category)
	 	    	.withCategory(category2)
	 	    	.withCategory(category3)
	 	    	.build();
	    
 	    // Need to merge .. to handle detached categories....
	    item =  itemService.update(item);
	    
	    List<Item> items = itemService.findAll();

		for (Item itema : items)   {
	 	    
		   System.out.println();
		   System.out.println("---------Item->Categories--------------");
		    System.out.println("Item Name : " + itema.getName());
	
			for (Category categorie : itema.getCategories()) {
			System.out.println("Category Name : " + categorie.getName());
			}
	
		}
		
		
 		category =  categoryService.findByCategoryName(category.getName());
 
		System.out.println("--------Category-> Items--------------");

		System.out.println("Category Name : " + category.getName());
		for (Item itema : category.getItems())  
			System.out.println("Item Name : " + itema.getName());

	}
}
