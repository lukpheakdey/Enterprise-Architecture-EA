package edu.mum.main;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Address;
import edu.mum.domain.Item;
import edu.mum.domain.User;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;

@Component
public class TestData {

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	

	public void setupData() {
	
		User userJohn = new User();
	    userJohn.setFirstName("John");
	    userJohn.setLastName("Doe");
	    userJohn.setEmail("john@Doe.com");
	    userService.save(userJohn);
 
	    Item itemCardboard = new Item();
 	    itemCardboard.setName("cardboard box");
	    itemCardboard.setInitialPrice(new BigDecimal(100));
	    
 	    Item itemPlastic = new Item();
 	    itemPlastic.setName("plastic box");
	    itemPlastic.setInitialPrice(new BigDecimal(50));
	    
 	    userJohn.getBoughtItems().add(itemCardboard);
	    userJohn.getBoughtItems().add(itemPlastic);
	    userService.update(userJohn);
//------------------------------------------------------
		User userSteve = new User();
	    userSteve.setFirstName("Steve");
	    userSteve.setLastName("Stag");
	    userSteve.setEmail("john@Doe.com");
	
	    
	    userService.save(userSteve);

	    
	    Item itemMetal = new Item();
 	    itemMetal.setName("metal box");
	    itemMetal.setInitialPrice(new BigDecimal(100));
	    
 	    Item itemJewelry = new Item();
 	    itemJewelry.setName("jewelry box");
	    itemJewelry.setInitialPrice(new BigDecimal(50));
	    
 	    Item itemRound = new Item();
 	    itemRound.setName("round box");
	    itemRound.setInitialPrice(new BigDecimal(50));
 
	    userSteve.getBoughtItems().add(itemJewelry);
	    userSteve.getBoughtItems().add(itemRound);
	    userService.update(userSteve);

	}
	
}
