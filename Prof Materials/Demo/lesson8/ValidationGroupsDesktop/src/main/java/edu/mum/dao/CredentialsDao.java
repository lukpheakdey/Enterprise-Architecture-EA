package edu.mum.dao;

import java.util.List;

import edu.mum.domain.UserCredentials;

public interface CredentialsDao extends GenericDao<UserCredentials> {
	public List<UserCredentials> findAllAdmin();
	public UserCredentials findByUserName(String userName);

 	}
