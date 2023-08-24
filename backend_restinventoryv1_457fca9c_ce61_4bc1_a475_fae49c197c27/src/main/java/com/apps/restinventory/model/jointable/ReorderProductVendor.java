package com.apps.restinventory.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

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

@Entity(name = "ReorderProductVendor")
@Table(schema = "\"restinventoryv1\"", name = "\"ReorderProductVendor\"")
@Data
public class ReorderProductVendor{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"ReOrderId\"")
	private Integer reOrderId;

    
    @Column(name = "\"Vendor\"")
    private String vendor;
 
}