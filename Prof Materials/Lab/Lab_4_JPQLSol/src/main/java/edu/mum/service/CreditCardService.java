package edu.mum.service;

import java.util.List;

import edu.mum.domain.CreditCard;
 
public interface CreditCardService {

	public void save(CreditCard creditCard);
	public void update(CreditCard creditCard);
	public List<CreditCard> findAll();
 
	public CreditCard findOne(Long id);

}
