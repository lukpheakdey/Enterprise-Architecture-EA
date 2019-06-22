package edu.mum.builder;

import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;

public class ProductBuilder {

     private Product product;
    
 	public ProductBuilder() {
		this.product = new Product();
	}

    public ProductBuilder withName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder withPrice(Float price) {
        this.product.setPrice(price);
        return this;
    }


    public ProductBuilder withStatus(ProductionStatus status) {
        this.product.setStatus(status);
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
