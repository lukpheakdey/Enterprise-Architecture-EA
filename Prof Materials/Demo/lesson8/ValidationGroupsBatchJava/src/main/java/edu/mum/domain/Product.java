package edu.mum.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import edu.mum.validation.EmptyOrSize;
import edu.mum.validation.NullMinNumber;
import edu.mum.validation.groups.Details;
import edu.mum.validation.groups.Production;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 5784L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
	@EmptyOrSize(min=5, max = 32, message= "{EmptyOrSize}")
	private String name;
	@EmptyOrSize(min=5, max = 32, message= "{EmptyOrSize}")
    private String manufacturer;
	
	@Range(min = 2 , max = 100 ,  message= "{NumberRange}")
    private BigDecimal weight;
   
    @Column(length = 2000)
	@EmptyOrSize(min=20, max = 2000, message= "{EmptyOrSize}", groups={Details.class})
    private String description;

    @Column(length = 255)
	@EmptyOrSize(min=8, max = 255, message= "{EmptyOrSize}", groups={Details.class})
    private String shortDescription;
    
	@EmptyOrSize(min=4, max = 8, message= "{EmptyOrSize}", groups={Details.class})
	private String productNumber;

 //   @Valid
 //   @NotEmpty(message= "{NotEmpty}", groups={ProductDefinition.class})
    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy="products")
    private List<Category> categories = new ArrayList<Category>();

    @Range(min = 2 , max = 1000 ,  message= "{NumberRange}", groups={Production.class})
    private BigDecimal price;
    
	@NullMinNumber(value=6 ,  message= "{NullMinNumber}", groups={Production.class})
    private Integer quantity;

	private ProductionStatus status = ProductionStatus.INVALID;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
    public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
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
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ProductionStatus getStatus() {
		return status;
	}
	public void setStatus(ProductionStatus status) {
		this.status = status;
	}
	
 }