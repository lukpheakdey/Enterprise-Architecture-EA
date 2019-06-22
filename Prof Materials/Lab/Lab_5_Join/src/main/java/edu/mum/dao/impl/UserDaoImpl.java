package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.UserDao;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setDaoType(User.class);
	}

 	public List<User> findAllJoinFetch() {
 		Query query =  entityManager.createQuery("SELECT u FROM User AS u JOIN FETCH u.item AS i");
		List<User> users =  query.getResultList();
 		return users;
	}
 	
// 	public User findByEmail(String email) {
//		Query query = entityManager.createQuery("select u from User u  where u.email =:email");
//		return (User) query.setParameter("email", email).getSingleResult();
//	}
 }