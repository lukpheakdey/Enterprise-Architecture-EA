package edu.mum.builder;

import edu.mum.domain.Category;
import edu.mum.domain.Item;

public class CategoryBuilder {

     private Category category;
    
 	public CategoryBuilder() {
		this.category = new Category();
	}

    public CategoryBuilder withName(String name) {
        this.category.setName(name);
        return this;
    }

     public CategoryBuilder withItem(Item item) {
        this.category.getItems().add(item);
        return this;
    }

     public Category build() {
        return category;
    }

	
}
