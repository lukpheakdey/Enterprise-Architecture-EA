package edu.mum.dao.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.domain.Member;
import edu.mum.domain.Product;

import edu.mum.dao.ProductDao;

	@Repository
	public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao
	{
		
		public ProductDaoImpl() {
			super.setDaoType(Product.class );
			}


	  public Product getProductByProductId(String key) {
		 return  this.getProductByProductId(key);
	  }
	
 		public List<Product> getAllProducts() {
	    	return this.findAll();
	    }
 	
	    public Product getProductById(String key) {	     
			Query query = entityManager.createQuery("select p from Product p  where p.productId =:productId");
			return (Product) query.setParameter("productId", key).getSingleResult();

	    }
		
		public List<Product> getProductsByCategory(String category) {
			
			Query query = entityManager.createQuery("select p from Product p where p.category = :category");
			 
			return (List<Product>) query.setParameter("category", category).getResultList();
 		}

 
		@SuppressWarnings("unchecked")
		public List<Product> getProductsByDescOrder() {
			Query query = entityManager.createQuery("select p from Product p order by p.productId desc");
			return (List<Product>) query.getResultList();

		}

 
	}

