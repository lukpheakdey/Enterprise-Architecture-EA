package edu.mum.dao;

import edu.mum.domain.Product;

public interface ProductDao extends GenericDao<Product> {
      
	public Product findByProductName(String name);
}
