package edu.mum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
 
 	@RequestMapping("")
	public List<Product> list(Model model) {
		return  productService.getAllProducts();
 
	}
	
 	@RequestMapping("/{id}")
	public Product getProductById( @PathVariable("id") Long productId) {

		return productService.get(productId);
 	}

 	@RequestMapping("/{id}/category")
	public Product getByIdWithCategory( @PathVariable("id") Long productId) {

		return productService.getWithCategory(productId);
 	}

	   
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewProductForm(@RequestBody Product productToBeAdded ) {

			productService.addProduct(productToBeAdded);
 
	}
	
   
}
