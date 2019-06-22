package edu.mum.domain;

import javax.persistence.*;

/**
 * This billing strategy can handle various credit cards.
 * <p>
 * The type of credit card is handled with a typesafe
 * enumeration, <tt>CreditCardType</tt>.
 *
 * @see CreditCardType
 * @author Christian Bauer
 */
@Entity(name="CREDIT_CARD")
 public class CreditCard extends BillingDetails {

 
    @Column( name = "CC_NUMBER", nullable = false, updatable = false, length = 16)
    private String number;

    @Column(name = "CC_EXP_MONTH", nullable = false, updatable = false, length = 2)
    private String expMonth="";

    @Column( name = "CC_EXP_YEAR", nullable = false, updatable = false, length = 4)
    private String expYear="";

    /**
     * No-arg constructor for JavaBean tools
     */
    public CreditCard() { super(); }

  
    // ********************** Accessor Methods ********************** //

 
    public String getNumber() { return number; }
    public void setNumber(String number) { 
    	this.number = number; 
    	}

    public String getExpMonth() { return expMonth; }

    public String getExpYear() { return expYear; }


	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

 
 }
