package edu.mum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

@Controller
public class ProductController {
 
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
    @RequestMapping(value={"/","/product"}, method = RequestMethod.GET)
    public String inputProduct(@ModelAttribute("newProduct") Product product,Model model) {
          List<Category> categories = categoryService.findAll();
          model.addAttribute("categories", categories);

          return "ProductForm";
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("newProduct") Product product ) {
    	Category category = categoryService.findOne(product.getCategory().getId());
        product.setCategory(category);
     	productService.save(product);
 
     	return "ProductDetails";
    }
    
    @RequestMapping(value="/listproducts", method = RequestMethod.GET)
    public String listProducts(Product product, Model model ) {
		List<Product> list = productService.getAllProducts();
		model.addAttribute("products",  list);
    	
        return "ListProducts";
    }
    
}
