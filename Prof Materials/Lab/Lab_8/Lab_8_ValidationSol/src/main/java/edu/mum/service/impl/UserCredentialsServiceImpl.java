package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.UserCredentialsDao;
import edu.mum.domain.UserCredentials;
import edu.mum.domain.UserCredentials;

@Service
@Transactional 
public class UserCredentialsServiceImpl implements edu.mum.service.UserCredentialsService {
	
 	@Autowired
	private UserCredentialsDao userCredentialsDao;

 	
    public void save( UserCredentials userCredentials) {  		
  		userCredentialsDao.save(userCredentials);
 	}
  	
  	
	public List<UserCredentials> findAll() {
		return (List<UserCredentials>)userCredentialsDao.findAll();
	}

	public UserCredentials findByUserName(String userName) {
		return userCredentialsDao.findByUserName(userName);
	}

	@Override
	public UserCredentials update(UserCredentials userCredentials) {
		return userCredentialsDao.update(userCredentials);
	
	}

}
