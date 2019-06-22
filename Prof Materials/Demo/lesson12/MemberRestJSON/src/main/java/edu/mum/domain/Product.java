package edu.mum.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@pid")
public class Product implements Serializable {
    private static final long serialVersionUID = 5784L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
	private String name;
    private String description;
    private String productId;
    private float price;

/*    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private Category category;
*/  
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="products")
/*    @JoinTable ( name="Category_Product", joinColumns={@JoinColumn(name="Product_ID")},  
    inverseJoinColumns={ @JoinColumn(name="Category_ID")} )  
*/
//	 @JsonIgnoreProperties(value="products")
    private List<Category> categories = new ArrayList<Category>();

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
    public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
/*	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}*/

	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void addCategory(Category category) {
		this.categories.add(category);
		category.getProducts().add(this);
	}

 }