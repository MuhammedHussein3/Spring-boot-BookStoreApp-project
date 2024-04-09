package com.bookify;

import com.bookify.enitity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookifyApplicationTests {


    @LocalServerPort
    private Long port;

    @Autowired
    private IntegrationTestH2BookRepo bookRepo;

    @Autowired
    private IntegrationTestH2CategoryRepo categoryRepo;

    private String url = "http://localhost";

    private static RestTemplate restTemplate;

    Logger log = LoggerFactory.getLogger(BookifyApplicationTests.class);

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }
    @BeforeEach
    public void setUp() {
        url = url.concat(":").concat(port+"");
    }

    @Test
    @Sql(statements = "DELETE FROM categories WHERE category_id IS NOT NULL ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addCategory(){
        Category category = Category.builder()
                .categoryId(1L)
                .name("software engineer")
                .description("computer science")
                .books(new ArrayList<>())
                .build();
        url.concat("/catalog");
        Category testCategory = restTemplate.postForObject(url.concat("/"), category, Category.class);
    }
//    @Test
//    @Sql(statements = "DELETE FROM books WHERE id IS NOT NULL ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void testAddBook() throws Exception {
//
//        Category category = Category.builder()
//                .categoryId(1L)
//                .name("software engineer")
//                .description("computer science")
//                .books(new ArrayList<>())
//                .build();
//       categoryRepo.save(category);
//        Book book = Book.builder()
//                .id(1L)
//                .name("java")
//                .authorName("oracle")
//                .categoryName("software engineer")
//                .publishBook(LocalDateTime.now())
//                .description("programming lang")
//                .edition("first edition")
//                .quantityInStock(23)
//                .price(1000d)
//                .priceAfterProduct(9725d)
//                .reviews(new ArrayList<>())
//                .createdBy("developer test")
//                .build();
//        Book testBook = restTemplate.postForObject(url+"/",book,Book.class);
//
//
//    }
    /**
     *     @Test
     *  *     @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id IS NOT NULL",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     *  *     public void testAddProduct(){
     *  *         Product product = Product.builder()
     *  *                 .id(1L)
     *  *                 .name("headset")
     *  *                 .quantity(2)
     *  *                 .price(79999d).build();
     *  *         Product testProduct = restTemplate.postForObject(baseUrl,product,Product.class);
     *  *         assertEquals("headset",testProduct.getName());
     *  *         assertEquals(1,h2Repo.findAll().size());
     *  *     }
     */

}
/**
 * package com.spring;
 *
 * import com.spring.entity.Product;
 * import org.junit.jupiter.api.BeforeAll;
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Order;
 * import org.junit.jupiter.api.Test;
 * import org.slf4j.Logger;
 * import org.slf4j.LoggerFactory;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.boot.test.web.server.LocalServerPort;
 * import org.springframework.test.context.jdbc.Sql;
 * import org.springframework.web.client.RestTemplate;
 *
 * import java.util.List;
 *
 * import static org.junit.jupiter.api.Assertions.*;
 *
 *
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * class SpringBootIntegrationTestApplicationTests {
 *     @LocalServerPort
 *     private long port;
 *     @Autowired
 *     private IntegrationTestH2Repository h2Repo;
 *     private String baseUrl = "http://localhost";
 *
 *     private static RestTemplate restTemplate;
 *     private Logger log = LoggerFactory.getLogger(SpringBootIntegrationTestApplicationTests.class);
 *
 *     @BeforeAll
 *     public static void init(){
 *         restTemplate = new RestTemplate();
 *     }
 *
 *
 *
 *     @BeforeEach
 *     public void setUp(){
 *         baseUrl=baseUrl.concat(":").concat(port+"").concat("/products");
 *     }
 *
 *     @Test
 *     @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id IS NOT NULL",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 *     public void testAddProduct(){
 *         Product product = Product.builder()
 *                 .id(1L)
 *                 .name("headset")
 *                 .quantity(2)
 *                 .price(79999d).build();
 *         Product testProduct = restTemplate.postForObject(baseUrl,product,Product.class);
 *         assertEquals("headset",testProduct.getName());
 *         assertEquals(1,h2Repo.findAll().size());
 *     }
 *
 *     @Test
 *     @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name, quantity, price) VALUES (1,'AC', 1, 34000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
 *     @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE name='AC'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 *     public void testGetProducts() {
 *         List<Product> products = restTemplate.getForObject(baseUrl, List.class);
 *         assertEquals(1, products.size());
 *         assertEquals(1, h2Repo.findAll().size());
 *     }
 *
 *
 *     @Test
 *     @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name,quantity,price) VALUES (10982,'Caffe',80,89871)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
 *     @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id = 10982",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 *     public void testFindProductById(){
 *         Product product = restTemplate.getForObject(baseUrl.concat("/{id}"),Product.class,10982);
 *         assertAll(
 *                 ()-> assertNotNull(product),
 *                 ()-> assertEquals("Caffe",product.getName()),
 *                 ()-> assertEquals(1,h2Repo.findAll().size()),
 *                 ()-> assertEquals(10982,product.getId())
 *
 *         );
 *
 *
 *     }
 *
 *
 *     @Test
 *     @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name,quantity,price) VALUES (91076,'pc',80,89871)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
 *     @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id = 10982",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 *     public void testUpdateProduct(){
 *        Product productUpdate = Product.builder()
 *                .id(null)
 *                .name("pc2")
 *                .quantity(32)
 *                .price(1111).build();
 *        restTemplate.put(baseUrl.concat("/update/{id}"),productUpdate,91076);
 *        Product testProduct = h2Repo.findById(91076L).orElse(null);
 *
 *        assertAll(
 *                ()->  assertNotNull(testProduct),
 *                ()->  assertEquals("pc2",testProduct.getName()),
 *                ()->  assertEquals(1111,testProduct.getPrice()),
 *                ()-> assertEquals(32,testProduct.getQuantity()),
 *                ()-> assertEquals(1,h2Repo.findAll().size())
 *        );
 *
 *     }
 *
 *     @Test
 *     @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name,quantity,price) VALUES (1,'car',2,20000052)")
 *     public void testDeleteProduct(){
 *
 *         restTemplate.delete(baseUrl.concat("/delete/{id}"),1L);
 *         Product product = h2Repo.findById(1L).orElse(null);
 *         assertAll(
 *                 ()->assertEquals(0,h2Repo.findAll().size()),
 *                 ()->assertEquals(null,product)
 *         );
 *
 *     }
 * }
 */
