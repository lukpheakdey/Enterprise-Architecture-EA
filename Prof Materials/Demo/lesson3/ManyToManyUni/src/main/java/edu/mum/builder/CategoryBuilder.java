package edu.mum.builder;

import edu.mum.domain.Category;
import edu.mum.domain.Product;

public class CategoryBuilder {

     private Category category;
    
 	public CategoryBuilder() {
		this.category = new Category();
	}

    public CategoryBuilder withName(String name) {
        this.category.setName(name);
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.category.setDescription(description);
        return this;
    }

    public CategoryBuilder withProduct(Product product) {
        this.category.getProducts().add(product);
        return this;
    }

      public CategoryBuilder withId(Long id) {
        this.category.setId(id);
        return this;
    }

    public Category build() {
        return category;
    }

	
}
