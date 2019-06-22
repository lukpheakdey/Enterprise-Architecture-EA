package edu.mum.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;

/**
 * The address of a User.
 *
 * An instance of this class is always associated with only
 * one <tt>User</tt> and depends on that parent objects lifecycle,
 * it is a component. Of course, other entity classes can also
 * embed addresses.
 *
 * @see User
 * @author Christian Bauer
 */
@Entity
public class Address implements Serializable {

    @Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
   private Long id = null;


    @Column(length = 255)
	private String street;

    @Range(min=10000, max = 99999999, message= "{Range.zipcode}")
    @Column(length = 16)
	private String zipcode;

    @Column(length = 255)
	private String city;

  	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  	private User  user;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public Address() {}

	/**
	 * Full constructor
	 */
	public Address(String street, String zipcode, String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	// ********************** Accessor Methods ********************** //

	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }

	public String getZipcode() { return zipcode; }
	public void setZipcode(String zipcode) { this.zipcode = zipcode; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

 
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// ********************** Business Methods ********************** //

}
