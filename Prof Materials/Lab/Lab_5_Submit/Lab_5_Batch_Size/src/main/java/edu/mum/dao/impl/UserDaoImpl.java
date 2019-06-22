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
		super.setDaoType(User.class );
	}

 	public List<User> findAllJoinFetch() {
		Query query =  entityManager.createQuery("SELECT u FROM User AS u JOIN FETCH u.boughtItems AS i");
		List<User> users =  query.getResultList();
		return users;
	}
 	
 	public List<User> findAllBatch() {
		List<User> users =  (List<User>)this.findAll();
 		// hydrate - need to access ALL since we don't know batch Size
		// e.g. if size =2  AND there are 6 members we need to hydrate members #1 & #3 & #5
       	for (User user : users)
       		if (!user.getBoughtItems().isEmpty()) user.getBoughtItems().get(0);
		
		// This will work for our example as there are size =2 & there are 3 members
		/*      members.get(0).getAddresses().get(0);
		      	members.get(2).getAddresses().get(0);
		*/
        return users;
	}

 }