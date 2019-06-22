package edu.mum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

@RestController
@RequestMapping({"/userCredentials"})
 public class UserCredentialsController {

	@Autowired
	UserCredentialsService credentialsService;
	
	@RequestMapping("")
	public @ResponseBody List<UserCredentials>  findAll( ) {
		return  credentialsService.findAll();
 
	}
	

 	
 	@RequestMapping("/{id}")
	public UserCredentials findOne(@PathVariable("id") String userName ) {

		UserCredentials validCredentials = credentialsService.findByUserName(userName);
 
 		return  validCredentials;
	}
 
 
}
