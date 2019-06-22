package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.ProductDao;
import edu.mum.domain.Product;

@Service
@Transactional 
public class ProductServiceImpl implements edu.mum.service.ProductService {
	
 	@Autowired
	private ProductDao productDao;

  	
    public void save( Product product) {  		
  		productDao.save(product);
 	}
  	
  	
    public void update( Product product) {  		
  		productDao.update(product);
 	}
  	
  	
	public List<Product> findAll() {
		return (List<Product>)productDao.findAll();
	}

	public Product findByProductName(String name) {
		return productDao.findByProductName(name);
	}
 
	public Product findOne(Long id) {
		return productDao.findOne(id);
	}
	
 
}
