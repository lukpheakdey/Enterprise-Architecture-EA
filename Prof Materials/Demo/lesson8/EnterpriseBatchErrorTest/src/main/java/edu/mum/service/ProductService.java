package edu.mum.service;

import java.util.List;

import edu.mum.domain.Product;
 
public interface ProductService {

	public Product save(Product product);
	public void update(Product product);
	public List<Product> findAll();
	public Product findByProductName(String name);

	public Product findOne(Long id);

}
