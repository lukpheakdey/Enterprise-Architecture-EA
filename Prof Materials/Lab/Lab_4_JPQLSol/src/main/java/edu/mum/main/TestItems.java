package edu.mum.main;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Category;
import edu.mum.domain.Item;
import edu.mum.domain.User;
import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;

@Component
public class TestItems {

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;

	
	public void setupItems() {
		
		   Category categorySports = new Category("Sports");	    
		categoryService.save(categorySports);
		   Category categoryToys = new Category("Toys");
			categoryService.save(categoryToys);
		    Category categoryGifts = new Category("Gifts");
			categoryService.save(categoryGifts);
 
		    User buyer = userService.findOne(2L); // "Jane Doe"
		    User seller = userService.findOne(1L);  // John Doe

	 	    //  Seller initial > 22 will be found by findItemsBySellOrBuy based on initialPrice...

		    Item itemSled = new Item();
		    itemSled.setName("Sled");
		    itemSled.setDescription("Winter time fun");
		    itemSled.setInitialPrice(new BigDecimal(28.0));
		    itemSled.setReservePrice(new BigDecimal(32.0));

		    itemSled.addCategory(categoryToys);
		    itemSled.addCategory(categorySports);
		    
		    itemSled.setSeller(seller);

		    itemService.update(itemSled);
		    
		// Second itemSkates
		    
		    // NEVER will be found  by findItemsBySellOrBuy

		    Item itemSkates = new Item();
		    itemSkates.setName("Skates");
		    itemSkates.setDescription("Winter time gliding");
		    itemSkates.setReservePrice(new BigDecimal(26.0));
		    itemSkates.setInitialPrice(new BigDecimal(22.0));

		    itemSkates.addCategory(categoryToys);
		    itemSkates.addCategory(categorySports);
 		    
		    // Need to merge .. to handle detached categories....
		    itemSkates =  itemService.update(itemSkates);
		    
		    
		    //  Buyer & Reserve = initial will be found by findItemsBySellOrBuy based on buyer...
		    Item itemShoes = new Item();
		    itemShoes.setName("Shoes");
		    itemShoes.setDescription("Snug Fit");
		    itemShoes.setReservePrice(new BigDecimal(18.0));
		    itemShoes.setInitialPrice(new BigDecimal(18.0));

		    itemShoes.addCategory(categoryGifts);
 		
		    buyer.addBoughtItem(itemShoes);
 		    userService.update(buyer);
 

	}
}
