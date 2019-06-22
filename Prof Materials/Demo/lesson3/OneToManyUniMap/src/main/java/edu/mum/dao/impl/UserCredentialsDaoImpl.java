package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.UserCredentialsDao;
import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;

 


@SuppressWarnings("unchecked")
@Repository
public class UserCredentialsDaoImpl extends GenericDaoImpl<UserCredentials> implements UserCredentialsDao {

	public UserCredentialsDaoImpl() {
		super.setDaoType(UserCredentials.class );
		}

	public UserCredentials findByUserName(String userName) {

		Query query = entityManager.createQuery("select m from CREDENTIALS m  where m.username =:userName");
		return (UserCredentials) query.setParameter("userName", userName).getSingleResult();

	}

	public UserCredentials findByMappedAuthority(String mapKey) {

		Query query = entityManager.createQuery("select c from CREDENTIALS c join c.authority ca where KEY(ca) =:mapKey");
		return (UserCredentials) query.setParameter("mapKey", mapKey).getSingleResult();

	}

   }