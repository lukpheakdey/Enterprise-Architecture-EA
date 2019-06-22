package edu.mum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Category {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
 	private long id;
    
    String name;
    String description;
    
    // If using a List INSTEAD of a SET - less efficient
/*    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable ( name="Category_Product", joinColumns={@JoinColumn(name="Category_ID")},  
    inverseJoinColumns={ @JoinColumn(name="Product_ID")} )  
*/
/*    @Transient
    Set<Product> products = new HashSet<Product>();
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

/*	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

 	public void addProduct(Product product) {
		this.products.add(product);
//		product.getCategories().add(this);
	}*/

}
