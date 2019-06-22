package edu.mum.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.UserCredentials;
import edu.mum.domain.Member;

	@Repository
	public interface CredentialsRepository extends  CrudRepository<UserCredentials, String> 
	{
  	}

