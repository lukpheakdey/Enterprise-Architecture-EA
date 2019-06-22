package edu.mum.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.mum.domain.Product;

@Component
public interface ProductRestService {

 	public List<Product> findAll();

	public Product findOne(Long index);

	public Product save(Product product);
	
	public Product getWithCategory(Long productId);


}
