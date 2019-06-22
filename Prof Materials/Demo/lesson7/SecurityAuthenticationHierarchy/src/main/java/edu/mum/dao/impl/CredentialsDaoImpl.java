package edu.mum.dao.impl;

 

import org.springframework.stereotype.Repository;

import edu.mum.dao.CredentialsDao;
import edu.mum.domain.UserCredentials;


@SuppressWarnings("unchecked")
@Repository
public class CredentialsDaoImpl extends GenericDaoImpl<UserCredentials> implements CredentialsDao {

	public CredentialsDaoImpl() {
		super.setDaoType(UserCredentials.class );
		}

 
 }