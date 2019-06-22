package edu.mum.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.domain.Product;
import edu.mum.repository.ProductRepository;

 public class ProductRepositoryImpl implements ProductRepository {
	
	private static final List<Product> listOfProducts = new ArrayList<Product>();
	 
	 // Private constructor. Prevents instantiation from other classes.
	 private ProductRepositoryImpl() {
		
	}
	 
	 // eager init'ed singleton...
	public final static ProductRepositoryImpl INSTANCE = new ProductRepositoryImpl();


	public List<Product> getAll() {
		return listOfProducts;
	}
	
	public void save(Product product) {
		listOfProducts.add(product);
		return ;
	}
	
	
		   
}
 
