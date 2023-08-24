package com.apps.restinventory.function;

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
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.apps.restinventory.repository.ReorderRepository;
import com.apps.restinventory.repository.ProductCategoryRepository;
import com.apps.restinventory.repository.PurchaseOrderRepository;
import com.apps.restinventory.repository.ProductStoreRepository;
import com.apps.restinventory.repository.VendorRepository;
import com.apps.restinventory.repository.DocumentRepository;
import com.apps.restinventory.repository.ProductCatalogueRepository;
import com.apps.restinventory.repository.InventoryStockRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
