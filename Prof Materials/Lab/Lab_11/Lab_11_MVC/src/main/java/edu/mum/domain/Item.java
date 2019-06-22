package edu.mum.domain;

 

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * An item for auction.
 *
 * @author Christian Bauer
 */
 
@NamedQuery(name="Item.findByCategory",
query="select i from Item i, Category c where c.name = :categoryName and i member of c.items" ) 


@Entity
@Table(name = "ITEM")
 public class Item   {

     @Id 
 	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private Long id = null;

    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;

    @Column(name = "ITEM_NAME", length = 255, nullable = false, updatable = false)
    private String name;

	@ManyToOne(fetch=FetchType.EAGER,  cascade = CascadeType.ALL) 
	@JoinColumn(name="itemSellerId") 
     private User seller;

@Transient
    private User buyer;

    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;

     private BigDecimal initialPrice;

     private BigDecimal reservePrice;

     @ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST,CascadeType.MERGE })
     private Set<Category> categories = new HashSet<Category>();

    @Transient
	private User approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVAL_DATETIME", nullable = true)
    private Date approvalDatetime;
 
@Transient
       private Collection<String> images = new ArrayList<String>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED", nullable = true, updatable = false)
    private Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = true, updatable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = true, updatable = false)
    private Date endDate;


 
    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }
    public int getVersion() { return version; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getSeller() { return seller; }

    public User getBuyer() { return buyer; }
    public void setBuyer(User buyer) { this.buyer = buyer; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getInitialPrice() { return initialPrice; }
    public void  setInitialPrice(BigDecimal initialPrice) { this.initialPrice =  initialPrice; }

    public BigDecimal getReservePrice() { return reservePrice; }

    public void setReservePrice(BigDecimal reservePrice) { this.reservePrice =  reservePrice; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

	public void addCategory(Category category) {
		this.categories.add(category);
		category.getItems().add(this);
	}

    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }

    public Date getApprovalDatetime() { return approvalDatetime; }
    public void setApprovalDatetime(Date approvalDatetime) { this.approvalDatetime = approvalDatetime; }

 
    // Read-only, modify through Category#addItem() and Category@removeItem();
    public Set<Category> getCategories() { return Collections.unmodifiableSet(categories); }

    public void setId(Long id) {
		this.id = id;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public Collection<String> getImages() { return images; }

    public Date getCreated() { return created; }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        final Item item = (Item) o;

        if (! (created.getTime() == item.created.getTime()) ) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + created.hashCode();
        return result;
    }

    public String toString() {
        return  "Item ('" + getId() + "'), " +
                "Name: '" + getName() + "' " +
                "Initial Price: '" + getInitialPrice()+ "'";
    }

    public int compareTo(Object o) {
        if (o instanceof Item) {
            // Don't compare Date objects! Use the time in milliseconds!
            return Long.valueOf(this.getCreated().getTime()).compareTo(
                    Long.valueOf( ((Item)o).getCreated().getTime())
                   );
        }
        return 0;
    }

    // ********************** Business Methods ********************** //

  
}
