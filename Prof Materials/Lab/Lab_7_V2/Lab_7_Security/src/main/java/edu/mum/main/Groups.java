package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Authority;
import edu.mum.domain.Group;
import edu.mum.domain.UserCredentials;
import edu.mum.service.GroupService;
import edu.mum.service.UserCredentialsService;

@Component
public class Groups {

	@Autowired
	GroupService groupService;
	
	@Autowired
	UserCredentialsService userCredentialsService;
	
	public void addGroups() {
	
		// Create ADMIN Group
		Group groupAdmin = new Group();
		groupAdmin.setGroup_name("ADMIN");
	
		// Create SUPERVISOR Group
		Group groupSuper = new Group();
		groupSuper.setGroup_name("SUPERVISOR");
	
	    // Add LIST to both groups
		Authority authority = new Authority();
		authority.setAuthority("LIST");
	    groupAdmin.getAuthority().add(authority);
	    groupSuper.getAuthority().add(authority);
		
	    // Add READ to both Groups
	    authority = new Authority();
	    authority.setAuthority("READ");
	    groupAdmin.getAuthority().add(authority);
	    groupSuper.getAuthority().add(authority);
	
	    // Add Update to Super only
	    authority = new Authority();
	    authority.setAuthority("UPDATE");
	    groupSuper.getAuthority().add(authority);
	    
	    
		// Add users to groups
	    groupAdmin.getUserCredentials().add(userCredentialsService.findByUserName("Sean"));
	    groupSuper.getUserCredentials().add(userCredentialsService.findByUserName("Paul"));
	   
		// Save groups
	    groupService.save(groupAdmin);
	    groupService.update(groupSuper);
	
	}
}
