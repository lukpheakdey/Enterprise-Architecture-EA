package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.CredentialsDao;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;


@SuppressWarnings("unchecked")
@Repository
public class CredentialsDaoImpl extends GenericDaoImpl<UserCredentials> implements CredentialsDao {

	public CredentialsDaoImpl() {
		super.setDaoType(UserCredentials.class );
		}

	public List<UserCredentials> findAllAdmin(){
		
		Query query = entityManager.createQuery("select uc from CREDENTIALS uc  where EXISTS (select a from uc.authority a where a.authority = 'ROLE_ADMIN')");
		return (List<UserCredentials>) query.getResultList();

	}
	
	public UserCredentials findByUserName(String userName) {

		Query query = entityManager.createQuery("select m from CREDENTIALS m  where m.username =:userName");
		return (UserCredentials) query.setParameter("userName", userName).getSingleResult();

	}

 }