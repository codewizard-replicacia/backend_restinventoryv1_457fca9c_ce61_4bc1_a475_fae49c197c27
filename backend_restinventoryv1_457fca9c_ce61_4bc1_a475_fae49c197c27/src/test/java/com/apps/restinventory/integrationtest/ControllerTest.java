package com.apps.restinventory.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.apps.restinventory.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/restinventoryv1/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/restinventoryv1/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("restinventoryv1", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateReorderInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReorderInstance.json"))
        .when()
        .post("/restinventoryv1/Reorders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReorder() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReorderInstance.json"))
        .when()
        .post("/restinventoryv1/Reorders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/Reorders?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ReOrderId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/Reorders/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateProductCategoryInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ProductCategoryInstance.json"))
        .when()
        .post("/restinventoryv1/ProductCategories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsProductCategory() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ProductCategoryInstance.json"))
        .when()
        .post("/restinventoryv1/ProductCategories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/ProductCategories?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ProductCategoryId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/ProductCategories/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePurchaseOrderInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PurchaseOrderInstance.json"))
        .when()
        .post("/restinventoryv1/PurchaseOrders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPurchaseOrder() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PurchaseOrderInstance.json"))
        .when()
        .post("/restinventoryv1/PurchaseOrders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/PurchaseOrders?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PurchaseOrderId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/PurchaseOrders/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateProductStoreInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ProductStoreInstance.json"))
        .when()
        .post("/restinventoryv1/ProductStores")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsProductStore() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ProductStoreInstance.json"))
        .when()
        .post("/restinventoryv1/ProductStores")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/ProductStores?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ProductStoreId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/ProductStores/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateVendorInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("VendorInstance.json"))
        .when()
        .post("/restinventoryv1/Vendors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsVendor() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("VendorInstance.json"))
        .when()
        .post("/restinventoryv1/Vendors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/Vendors?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Vendor", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/Vendors/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDocumentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/restinventoryv1/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDocument() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/restinventoryv1/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/Documents?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/Documents/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateProductCatalogueInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ProductCatalogueInstance.json"))
        .when()
        .post("/restinventoryv1/ProductCatalogues")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsProductCatalogue() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ProductCatalogueInstance.json"))
        .when()
        .post("/restinventoryv1/ProductCatalogues")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/ProductCatalogues?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ProductId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/ProductCatalogues/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateInventoryStockInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("InventoryStockInstance.json"))
        .when()
        .post("/restinventoryv1/InventoryStocks")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsInventoryStock() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("InventoryStockInstance.json"))
        .when()
        .post("/restinventoryv1/InventoryStocks")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/restinventoryv1/InventoryStocks?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).InventoryId", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/restinventoryv1/InventoryStocks/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM restinventoryv1.Reorder");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.ProductCategory");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.PurchaseOrder");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.ProductStore");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.Vendor");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.Document");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.ProductCatalogue");
    jdbcTemplate.execute("DELETE FROM restinventoryv1.InventoryStock");
     jdbcTemplate.execute("DELETE FROM restinventoryv1.ReorderProductVendor");
     jdbcTemplate.execute("DELETE FROM restinventoryv1.ProductStoreProducts");
     jdbcTemplate.execute("DELETE FROM restinventoryv1.VendorOrderdetails");
     jdbcTemplate.execute("DELETE FROM restinventoryv1.ProductCatalogueStock");
     jdbcTemplate.execute("DELETE FROM restinventoryv1.InventoryStockStockalert");

    RestAssuredMockMvc.reset();
  }
}
