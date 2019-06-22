package edu.mum.rest.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;
 
@Component
@Path("/products")
public class ProductRestService {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
 	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts()
	{	 
		return productService.findAll();
	}

	@GET
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("id") Long id)
	{
		return productService.findOne(id);
 	}
	
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
	public Product saveProduct(Product product)
	{
		 
		 productService.save(product);
		return null;
	}
 

	/*
	 * Initialize the domain model/ database ...
	 * on Start
	 */
	@PostConstruct
	private void intializeModel() {
	    Category category = new Category();
	    category.setName("Sports");
	    
	    Category category2 = new Category();
	    category2.setName("Toys");
	    
	 
	    Product product = new Product();
	    product.setName("Sled");
	    product.setDescription("Winter time fun");
	    product.setPrice(22.0F);
	    
	    product.addCategory(category2);
	    product.addCategory(category);

	    	productService.save(product);
	    
	 // Second product
	    
	    Product product2 = new Product();
	    product2.setName("Skates");
	    product2.setDescription("Winter time gliding");
	    product2.setPrice(44.0F);
	    product2.setStatus(ProductionStatus.PRODUCTION);

	    product2.addCategory(category2);
	    product2.addCategory(category);
	    
    	product2 = productService.update(product2);
 
	}

 
}
