package edu.mum.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.mum.domain.Product;

public interface ProductService {

	List<Product> findAll();

//	Product getProductById(String productID);
	
//	List<Product> getProductsByCategory(String category);

 	void addProduct(Product product);

	Product findOne(long id);
 
//	List<Product> getProductsByDescOrder();

	public Product getWithCategory(Long productId);

}
