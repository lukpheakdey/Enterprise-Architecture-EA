package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.CredentialsDao;
import edu.mum.domain.UserCredentials;
import edu.mum.service.CredentialsService;

@Service
@Transactional 
public class CredentialsServiceImpl implements CredentialsService {
	
 	@Autowired
	private CredentialsDao credentialsDao;

//  	@PreAuthorize("hasRole('ROLE_ADMIN')")
  	public void save(UserCredentials credentials) {

   		credentialsDao.save(credentials);
	}
	public List<UserCredentials> findAll() {
		return (List<UserCredentials>)credentialsDao.findAll();
	}

 
}
