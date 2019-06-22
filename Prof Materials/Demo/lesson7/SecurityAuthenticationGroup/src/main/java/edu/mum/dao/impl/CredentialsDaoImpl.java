package edu.mum.dao.impl;

 

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.CredentialsDao;
import edu.mum.domain.UserCredentials;


@SuppressWarnings("unchecked")
@Repository
public class CredentialsDaoImpl extends GenericDaoImpl<UserCredentials> implements CredentialsDao {

	public CredentialsDaoImpl() {
		super.setDaoType(UserCredentials.class );
		}

	@Override
	public UserCredentials getByUsername(String name){
	       Query query = entityManager.createQuery( "select u from  CREDENTIALS u where u.username = :name" );
	    		  
	       return (UserCredentials) query.setParameter("name",name)
			       .getSingleResult();

	}
 }