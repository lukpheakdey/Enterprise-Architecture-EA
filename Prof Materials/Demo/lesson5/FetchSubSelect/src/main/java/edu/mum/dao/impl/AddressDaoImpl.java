package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.AddressDao;
import edu.mum.domain.Address;
import edu.mum.domain.Member;


@SuppressWarnings("unchecked")
@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

	public AddressDaoImpl() {
		super.setDaoType(Address.class );
		}

	// "Manual" example of Hibernate FetchMode.SUBSELECT
	public List<Address> findAddressBySubSelect() {
		  Query query =  entityManager.createQuery("SELECT a FROM Address AS a where a.member in (SELECT m FROM Member AS m) ORDER BY a.member ASC");
		  return (List<Address>) query.getResultList();
 	}
 
 }