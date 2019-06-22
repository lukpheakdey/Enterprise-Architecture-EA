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

	public List<Address> findAllOuterJoinFetch() {
//		  Query query =  entityManager.createQuery("SELECT DISTINCT  m FROM Address AS m LEFT JOIN FETCH m.member AS a order by m.member.firstName DESC");
		  Query query =  entityManager.createQuery("SELECT DISTINCT  a FROM Address AS a LEFT JOIN FETCH a.member  order by a.member.firstName DESC");
		  return (List<Address>) query.getResultList();

	}

 
 }