package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.dao.ProductDao;
import edu.mum.domain.Product;
import edu.mum.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductDao productRepository;
	
	public void processOrder(String productId, long quantity) {
		Product productById = productRepository.getProductByProductId(productId);
		
 //		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}
}
