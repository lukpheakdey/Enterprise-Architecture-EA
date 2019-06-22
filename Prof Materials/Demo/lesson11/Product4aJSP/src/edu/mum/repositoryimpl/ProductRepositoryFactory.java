package edu.mum.repositoryimpl;

import edu.mum.repository.ProductRepository;

 public class ProductRepositoryFactory   {
	  
 	public static final ProductRepository getProductRepository() {
 		// if dev then
		return ProductRepositoryImpl.INSTANCE;
		// could have else for Prod..prod2, etc.
	}
}
 
