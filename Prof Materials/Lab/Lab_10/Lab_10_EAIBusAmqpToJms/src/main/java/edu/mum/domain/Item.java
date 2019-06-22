package edu.mum.domain;

import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id = null;

	private int version = 0;

	private String name;

	private String description;
	
	private double price;

	public Item() {

	}

	public Item(double price, String name, String description) {
		this.price = price;
		this.name = name;
		this.description = description;

	}


	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Item))
			return false;

		final Item item = (Item) o;

		if (name != null ? !name.equals(item.name) : item.name != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (name != null ? name.hashCode() : 0);
		return result;
	}

	public String toString() {
		return "Item ('" + getId() + "'), " + "Name: '" + getName() + "' " + "'";
	}

}
