package edu.mum.domain;

import java.util.HashSet;
import java.util.Set;


 
public class Category {

 
 	private long id;
    
    String name;
    String description;
    
 
    Set<Product> products = new HashSet<Product>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

 	public void addProduct(Product product) {
		this.products.add(product);
//		product.getCategories().add(this);
	}

}
