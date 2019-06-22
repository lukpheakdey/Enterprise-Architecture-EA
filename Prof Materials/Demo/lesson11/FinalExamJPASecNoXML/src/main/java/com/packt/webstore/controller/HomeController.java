package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.service.EmployeeService;
  
@Controller
public class HomeController {

	
  	@Autowired
 	private EmployeeService customerService;

	@RequestMapping({"/","/welcome"})
	public String welcome(Model model) {
		
		
		model.addAttribute("greeting", "Welcome to the Lone Ranger Company, Kimosabe!!");
		model.addAttribute("tagline", "The one and only place to work, so you can live and play!!");

		return "welcome";
	}
	
}
