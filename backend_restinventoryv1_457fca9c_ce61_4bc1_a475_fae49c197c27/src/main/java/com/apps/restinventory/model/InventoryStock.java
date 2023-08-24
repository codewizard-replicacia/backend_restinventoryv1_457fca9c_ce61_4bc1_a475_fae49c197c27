package com.apps.restinventory.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.apps.restinventory.model.Document;
import com.apps.restinventory.model.ProductCatalogue;
import com.apps.restinventory.model.Vendor;
import com.apps.restinventory.model.Reorder;
import com.apps.restinventory.model.InventoryStock;
import com.apps.restinventory.model.PurchaseOrder;
import com.apps.restinventory.model.ProductStore;
import com.apps.restinventory.model.ProductCategory;
import com.apps.restinventory.enums.Product;
import com.apps.restinventory.converter.ProductConverter;
import com.apps.restinventory.converter.DurationConverter;
import com.apps.restinventory.converter.UUIDToByteConverter;
import com.apps.restinventory.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "InventoryStock")
@Table(name = "\"InventoryStock\"", schema =  "\"restinventoryv1\"")
@Data
                        
public class InventoryStock {
	public InventoryStock () {   
  }
	  
  @Id
  @Column(name = "\"InventoryId\"", nullable = true )
  private String inventoryId;
	  
  @Column(name = "\"Perishable\"", nullable = true )
  private Boolean perishable;
  
	  
  @Column(name = "\"ExpirtyDate\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date expirtyDate;  
  
	  
  @Column(name = "\"PurchasedDate\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date purchasedDate;  
  
	  
  @Column(name = "\"Vendor\"", nullable = true )
  private String vendor;
  
	  
  @Column(name = "\"VendorContact\"", nullable = true )
  private Long vendorContact;
  
	  
  @Column(name = "\"CurrentStockUnits\"", nullable = true )
  private Integer currentStockUnits;
  
	  
  @Column(name = "\"InStockunits\"", nullable = true )
  private String inStockunits;
  
  
  
  
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"InventoryStockStockalert\"",
            joinColumns = @JoinColumn( name="\"InventoryId\""),
            inverseJoinColumns = @JoinColumn( name="\"ReOrderId\""), schema = "\"restinventoryv1\"")
private List<Reorder> stockalert = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "InventoryStock [" 
  + "InventoryId= " + inventoryId  + ", " 
  + "Perishable= " + perishable  + ", " 
  + "ExpirtyDate= " + expirtyDate  + ", " 
  + "PurchasedDate= " + purchasedDate  + ", " 
  + "Vendor= " + vendor  + ", " 
  + "VendorContact= " + vendorContact  + ", " 
  + "CurrentStockUnits= " + currentStockUnits  + ", " 
  + "InStockunits= " + inStockunits 
 + "]";
	}
	
}