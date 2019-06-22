package edu.mum.dao.impl;

import edu.mum.dao.GenericDao;
import edu.mum.domain.UserCredentials;

public interface UserCredentialsDao extends GenericDao<UserCredentials> {
  
	public UserCredentials findByUserName(String userName);
}
