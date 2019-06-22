package edu.mum.service;

import java.util.List;

import edu.mum.domain.BankAccount;
 
public interface BankAccountService {

	public void save(BankAccount bankAccount);
	public void update(BankAccount bankAccount);
	public List<BankAccount> findAll();
 
	public BankAccount findOne(Long id);

}
