package edu.mum.validation.dto;

import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;

public class ProductValidationDto implements ValidationDto{

	private Product product;
	
	ProductValidationDto() {}

	public ProductValidationDto(Product product) {
		this.setProduct(product);
	}
	
	@Override
	public Long getId() {
		return product.getId();
 	}

	@Override
	public ProductionStatus getStatus() {
		return product.getStatus();
 	}

	@Override
	public void setStatus(ProductionStatus status) {
		product.setStatus(status);
		
	}

	@Override
	public Object getValidationObject() {
		 
		return product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
