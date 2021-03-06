package edu.mum.service;

import java.util.List;

import edu.mum.domain.UserCredentials;
 
public interface CredentialsService {

	public void save(UserCredentials credentials);
	public List<UserCredentials> findAll();
	public UserCredentials findOne(String id);
	public List<UserCredentials> findAllAdmin();
	public UserCredentials findByUserName(String userName);
 }
