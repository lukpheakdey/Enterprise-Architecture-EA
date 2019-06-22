package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.User;

@Service
@Transactional  (rollbackFor= Exception.class)
public class UserServiceImpl implements edu.mum.service.UserService {
	
 	@Autowired
	private UserDao userDao;

 	
     public void save( User user) {  		
  		userDao.save(user);
 	}
  	
  	
	public List<User> findAll() {
		return (List<User>)userDao.findAll();
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public User update(User user) {
		 return userDao.update(user);

	}

	public void refresh(User user) {
		userDao.refresh(user);
	}
	public void flush() {
		userDao.flush();
	}

 	public void testRefresh(User user) throws Exception {
 		
 		System.out.println();
		System.out.println("Starting value: User email == " + user.getEmail());
 		userDao.save(user);
 
 		user.setEmail("refresh");
 		System.out.println();
		System.out.println("After persist SET value: User email == " + user.getEmail());

		try {
 		this.refresh(user);
 		System.out.println();
		System.out.println("After refresh value: User email == " + user.getEmail());
		}
		catch (JpaObjectRetrievalFailureException e) {
			System.out.println("EXCEPTION can't refresh new User is in cache NOT DB");
	 		System.out.println();
		}
 		
 		this.flush();   // force the SQL insert and triggers to run
 		this.refresh(user);
 		System.out.println();
 		System.out.println("After flush/refresh value: User email == " + user.getEmail());


	}
 

}
