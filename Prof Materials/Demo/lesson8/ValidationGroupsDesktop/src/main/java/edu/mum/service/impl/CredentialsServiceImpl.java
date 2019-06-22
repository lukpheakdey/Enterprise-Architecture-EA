package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();   		
  		String encodedPassword = passwordEncoder.encode(credentials.getPassword());
  		credentials.setPassword(encodedPassword);
  		
  		credentialsDao.save(credentials);
	}
	public List<UserCredentials> findAll() {
		return (List<UserCredentials>)credentialsDao.findAll();
	}

	public List<UserCredentials> findAllAdmin() {
		return (List<UserCredentials>)credentialsDao.findAllAdmin();
	}

	public UserCredentials findOne(String id) {
		return credentialsDao.findOne(id);
	}
 
	public UserCredentials findByUserName(String userName) {
		return credentialsDao.findByUserName(userName);
	}


}
