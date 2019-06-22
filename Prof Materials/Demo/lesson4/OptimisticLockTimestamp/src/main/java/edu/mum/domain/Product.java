package edu.mum.domain;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.SourceType;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 5784L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	private String name;
    private String description;
    private float price;

    // Timestamp generated in DB NSTEAD of JVM:
  // version  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  
  @Version
    @org.hibernate.annotations.Source(SourceType.DB)
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
     @Column(insertable=false,updatable=false, 
        columnDefinition="timestamp(6) default CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
//	     @Temporal(TemporalType.TIMESTAMP)
     private Timestamp version;    
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
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
	public Timestamp getVersion() {
		return version;
	}
	public void setVersion(Timestamp version) {
		this.version = version;
	}
	
 }