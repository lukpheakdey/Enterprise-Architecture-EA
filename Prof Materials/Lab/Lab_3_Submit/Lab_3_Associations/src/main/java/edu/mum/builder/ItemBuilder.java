package edu.mum.builder;

import java.math.BigDecimal;

import edu.mum.domain.Category;
import edu.mum.domain.Item;

public class ItemBuilder {

     private Item item;
    
 	public ItemBuilder() {
		this.item = new Item();
	}

    public ItemBuilder withName(String name) {
        this.item.setName(name);
        return this;
    }

    public ItemBuilder withDescription(String description) {
        this.item.setDescription(description);
        return this;
    }

    public ItemBuilder withInitialPrice(BigDecimal price) {
        this.item.setInitialPrice(price);
        return this;
    }

    public ItemBuilder withCategory(Category category) {
        this.item.addCategory(category);
        return this;
    }


      public Item build() {
        return item;
    }

	
}
