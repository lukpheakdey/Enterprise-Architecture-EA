package edu.mum.test.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.ProductService;

/*
 * Declared in user-job.xml
 */
public class ProductItemWriterRestart implements ItemWriter<Product>   {

 	private ProductService productService;

 	// Set in BatchJobRestart to simulate functionality /error
 	private Integer exceptionCounter = 0;
 	// Class level  product counter  - "5" is commit interval - 
 	// so we throw skip exceptions on second commit...
 	// Restart will pick up at start of second chunk
 	private Integer productCounter = 0;
 	
	@Override
	public void write(List<? extends Product> products) throws Exception {
		
		for (Product product : products) {
 			if (productCounter > 5 && exceptionCounter-- > 0 ) {
 				throw new Exception();
			}
			productCounter++;
 			productService.save(product);
		}
	}

	// Injected in user-job.xml
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getExceptionCounter() {
		return exceptionCounter;
	}

	public void setExceptionCounter(Integer counter) {
		this.exceptionCounter = counter;
	}


}

