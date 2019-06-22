package edu.mum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.domain.User;
import edu.mum.service.UserService;

@RestController
@RequestMapping({"/users"})
public class UserController {
	
	@Autowired
	private UserService  userService;

	@RequestMapping
	public List<User>  listUsers(Model model) {
		return userService.findAll();
	}
	
  	@RequestMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.findOne(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public User processAddNewUserForm(@RequestBody User userToBeAdded) {
			userService.save(userToBeAdded);

	   	return null;
 
	}
	
 
}
