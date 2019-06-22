package edu.mum.dao;

import java.util.List;

import edu.mum.domain.User;

public interface UserDao extends GenericDao<User> {
      
 	public List<User> findAllJoinFetch();
}
