package edu.mum.repository;

import java.util.List;

import edu.mum.domain.Product;

 public interface ProductRepository   {
	
 
	
	public List<Product> getAll();
	
	public void save(Product product);
	
	
		   
}
 
