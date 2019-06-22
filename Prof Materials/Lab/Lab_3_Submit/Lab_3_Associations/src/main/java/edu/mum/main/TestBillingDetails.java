package edu.mum.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.BankAccount;
import edu.mum.domain.CreditCard;
import edu.mum.domain.User;
import edu.mum.domain.UserCredentials;
import edu.mum.service.BankAccountService;
import edu.mum.service.CreditCardService;
import edu.mum.service.UserCredentialsService;
import edu.mum.service.UserService;

@Component
public class TestBillingDetails {

	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	CreditCardService creditCardService;
	

 public void testBillingDetails() {
 
	 BankAccount bankAccount = new BankAccount();
	 bankAccount.setOwner("Big Buck");
	 bankAccount.setAccount("BANK");
	 
	 bankAccountService.save(bankAccount);
	 
	 CreditCard creditCard = new CreditCard();
	 creditCard.setOwner("Big Debt");
	 creditCard.setNumber("1234567");
 	 
	 creditCardService.save(creditCard);
	 
	 System.out.println();
	   System.out.println("        *********  Bank Account Owner Name **********");

	   bankAccount = bankAccountService.findOne(bankAccount.getId());
	   
	   System.out.println("Bank Account Name: " + bankAccount.getAccount()  );
	   System.out.println("Owner Name: " + bankAccount.getOwner()  );
	   
		 System.out.println();
		 System.out.println("        *********  Credit Card Owner Name **********");

		   creditCard = creditCardService.findOne(creditCard.getId());

		   System.out.println("Credit Card Number: " + creditCard.getNumber()  );
		   System.out.println("Owner Name: " + creditCard.getOwner()  );
		   
}
}
