package edu.mum.service;

import java.util.List;

import edu.mum.domain.Group;
import edu.mum.domain.UserCredentials;
 
public interface GroupService {

	public void save(Group group);
	public void update(Group group);
	public List<Group> findAll();
 }
