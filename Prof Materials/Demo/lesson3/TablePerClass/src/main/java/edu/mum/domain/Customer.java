package edu.mum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

//Has MemberRole & Customer in Customer DB TABLE

@Entity
 public class Customer extends MemberRole {

/*	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private long id;
		
*/
	public Customer() {}
	
	public Customer(String nickName, Integer bonusPoints) {
		super.setNickName(nickName);
		super.setType("Customer");
		this.setBonusPoints(bonusPoints);
		
	}
	
//	private List<PaymentTypes> paymentTypes;
	
	private Integer bonusPoints;
	
	/*    @OneToMany(fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	*/
	 	@Transient
	 	private List<Order> orders =  new ArrayList<Order>();
	
/*	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

*/	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



}
