package edu.mum.dao.impl;

 

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.CreditCardDao;
import edu.mum.domain.CreditCard;


@SuppressWarnings("unchecked")
@Repository
public class CreditCardDaoImpl extends GenericDaoImpl<CreditCard> implements CreditCardDao {

	public CreditCardDaoImpl() {
		super.setDaoType(CreditCard.class );
		}

 
 }