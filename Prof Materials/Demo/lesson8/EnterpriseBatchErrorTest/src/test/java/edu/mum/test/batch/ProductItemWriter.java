package edu.mum.test.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.ProductService;

/*
 * Declared in user-job.xml
 */
public class ProductItemWriter implements ItemWriter<Product>   {

 	private ProductService productService;

 	// Set in Test...to simulate functionality/errors
 	private Integer counter = 0;
 	
	@Override
	public void write(List<? extends Product> products) throws Exception {
		
		for (Product product : products) {
 			if (counter-- > 0 ) {
 				throw new Exception();
			}
 			productService.save(product);
		}
	}

	// Injected in user-job.xml
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}


}

