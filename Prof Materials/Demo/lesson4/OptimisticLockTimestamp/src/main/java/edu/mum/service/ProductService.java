package edu.mum.service;

import java.util.List;

import edu.mum.domain.Product;
 
public interface ProductService {

	public void save(Product product);
	public Product update(Product product);
	public List<Product> findAll();
	public Product findByProductName(String name);

	public Product findOne(Long id);

}
