package edu.mum.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Item;
import edu.mum.service.ItemService;

@Component
public class TestItemCategory {

	@Autowired
	ItemService itemService;
	
	public void testItemCategory() {
		String categoryName = "Sports";
		
 				   List<Item> items =  itemService.findByCategoryName(categoryName);
	
				 	System.out.println();
				    System.out.println("********** NamedQuery -> Items by Category : " + categoryName + " ***************");
 				 	System.out.println();

					for (Item iteme : items) {
					System.out.println("Item Name : " + iteme.getName());
					}

	}
}
