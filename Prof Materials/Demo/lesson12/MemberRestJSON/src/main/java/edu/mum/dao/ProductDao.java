package edu.mum.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;

 import edu.mum.domain.Product;

import edu.mum.domain.Member;


	public interface ProductDao extends GenericDao<Product>  
	{
	  Product getProductByProductId(String key);
	
		public List<Product> getAllProducts();
 	
	    Product getProductById(String key);
		
		List<Product> getProductsByCategory(String category);

  
		List<Product> getProductsByDescOrder();

 
	}

