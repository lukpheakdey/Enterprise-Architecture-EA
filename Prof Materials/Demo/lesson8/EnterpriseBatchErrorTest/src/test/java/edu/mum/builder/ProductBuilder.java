package edu.mum.builder;

import edu.mum.domain.Category;
import edu.mum.domain.Product;

public class ProductBuilder {

     private Product product;
    
 	public ProductBuilder() {
		this.product = new Product();
	}

 	
    public ProductBuilder withCategory(Category category) {
        this.product.addCategory(category);
        return this;
    }

    public ProductBuilder withName(String name) {
        this.product.setName(name);
        return this;
    }


    public ProductBuilder withDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder withId(Long id) {
        this.product.setId(id);
        return this;
    }

    public Product build() {
        return product;
    }

	
}
