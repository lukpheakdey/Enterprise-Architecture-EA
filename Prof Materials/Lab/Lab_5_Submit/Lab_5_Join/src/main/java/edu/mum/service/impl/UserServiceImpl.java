package edu.mum.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.User;

@Service
@Transactional 
public class UserServiceImpl implements edu.mum.service.UserService {
	
 	@Autowired
	private UserDao userDao;

 	
     public void save( User user) {  		
  		userDao.save(user);
 	}
  	
  	
	public List<User> findAll() {
		return (List<User>)userDao.findAll();
	}

 	public User update(User user) {
		 return userDao.update(user);

	}

	
	public List<User> findAllJoinFetch() {
		 return userDao.findAllJoinFetch();

 	}

}
