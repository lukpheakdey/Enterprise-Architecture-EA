package edu.mum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.domain.Item;
import edu.mum.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
 
 	@GetMapping({"","/all"})
	public List<Item> list(Model model) {
		return itemService.findAll();

	}
	
 	@GetMapping("/{id}")
	public Item getItemById(@PathVariable("id") Long id) {

		return itemService.findOne(id);
	}

	@PostMapping("/add")
	public Item processAddNewItemForm(@RequestBody Item itemToBeAdded) {
 
 		try {
			itemService.save(itemToBeAdded);
		} catch (Exception up) {
	      System.out.println("Transaction Failed!!!");
 
		}
		
	   	return null;
	}
	
   
}
