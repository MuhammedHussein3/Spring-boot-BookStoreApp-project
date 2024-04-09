package com.bookify.service.impl;

import com.bookify.enitity.Book;
import com.bookify.repository.BookRepo;
import com.bookify.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;
    @MockBean
    BookRepo bookRepo;

    Book book;
    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(1L)
                .name("Java")
                .authorName("oracle")
                .edition("First edition")
                .publishBook(null)
                .price(1000d)
                .categoryName("software engineer")
                .priceAfterProduct(9234)
                .createdBy("developer test")
                .booksCount(55)
                .reviews(new ArrayList<>())
                .quantityInStock(42)
                .createDate(null)
                .lastModifiedBy(null)
                .lastModifiedDate(null).build();
    }
    @Test
    void updateProduct() {
        Book book_Update = new Book();
        book_Update = Book.builder().id(1L).name("Java").authorName("James Gosling").edition("First edition").publishBook(null).price(1000d).categoryName("software engineer").priceAfterProduct(9234).createdBy("developer test")
                .booksCount(100).reviews(new ArrayList<>()).quantityInStock(99).createDate(null).lastModifiedBy(null).lastModifiedDate(null).build();

        when(bookRepo.save(book_Update)).thenReturn(book_Update);
        Book finalBook_Update = book_Update;
        assertAll(
//                ()->assertEquals("Updated successfully",bookService.updateProduct(finalBook_Update))
//                ()->assertEquals("James Gosling", finalBook_Update.getAuthorName()),
//                ()->assertEquals(100,finalBook_Update.getBooksCount())
        );

    }
}
/**

 *
 * @SpringBootTest
 * class CloudVendorServiceImplTest {
 *
 *      @MockBean
 *      CloudVendorRepo cloudVendorRepo;
 *      @Autowired
 *      CloudVendorService cloudVendorService;
 *      CloudVendor cloudVendor;
 *
 *     @BeforeEach
 *     void setUp() {
 *         cloudVendor = new CloudVendor
 *                 ("1","Amzon","falstin","23423");
 *     }
 *
 *     @AfterEach
 *     void tearDown(){
 *
 *     }
 *
 *
 *     @Test
 *     void TestCreateCloudVendor() {
 *
 *         when(cloudVendorRepo.save(Mockito.any(CloudVendor.class))).thenReturn(cloudVendor);
 *         assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
 *     }
 *
 *     @Test
 *     void updateCloudVendor() {
 *       CloudVendor cloudVendor_up = new CloudVendor("1","google","falstin","23423");
 *         when(cloudVendorRepo.save(cloudVendor_up)).thenReturn(cloudVendor_up);
 *         assertAll(
 *                 ()-> assertEquals("Success",cloudVendorService.updateCloudVendor(cloudVendor_up)));
 *     }
 *
 *     @Test
 *     void deleteCloudVendor() {
 *      doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepo)
 *              .deleteById("1");
 *
 *         assertAll(
 *                 ()-> assertEquals("Success",cloudVendorService.deleteCloudVendor("1")),
 *                 ()-> assertEquals(0,cloudVendorRepo.findAll().size()));
 *
 *     }
 *
 *     @Test
 *     void getCloudVendor() {
 *
 *
 *         when(cloudVendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
 *
 *         assertThat(cloudVendorService.getCloudVendor("1").getVendorName())
 *                 .isEqualTo(cloudVendor.getVendorName());
 *
 *     }
 *     @Test
 *     void testGetCloudVendor_notFound() {
 *
 *         doThrow(new CloudVendorNotFoundException("Requested Cloud Vendor does not exist")).when(cloudVendorRepo)
 *                 .findById("2");
 *         assertThrows(CloudVendorNotFoundException.class,()->cloudVendorService.getCloudVendor("2"));
 *
 *     }
 *     @Test
 *     void testGetCloudVendor_notFound2() {
 *
 *         Mockito.when(cloudVendorRepo.findById("2")).thenThrow(new CloudVendorNotFoundException("Requested Cloud Vendor does not exist"));
 *         assertThrows(CloudVendorNotFoundException.class,()->cloudVendorService.getCloudVendor("2"));
 *     }
 *
 *     @Test
 *     void getAllCloudVendors() {
 *         Mockito.when(cloudVendorRepo.findAll()).thenReturn(List.of(cloudVendor));
 *         List<CloudVendor> cvs = cloudVendorService.getAllCloudVendors();
 *         assertAll(
 *                 ()-> assertEquals(1,cloudVendorRepo.findAll().size()),
 *                ()-> assertEquals(1,cloudVendorService.getAllCloudVendors().size()),
 *                 ()-> assertEquals("1",cvs.get(0).getVendorId()),
 *                 ()->  assertEquals("Amzon",cvs.get(0).getVendorName()),
 *                 ()-> assertEquals("falstin",cvs.get(0).getVendorAddress()),
 *                 ()-> assertEquals("23423",cvs.get(0).getVendorPhoneNumber())
 *         );
 *     }
 *
 *     @Test
 *     void getByVendorName() {
 *
 *         when(cloudVendorRepo.findByVendorName("Amzon")).
 *                 thenReturn(List.of(cloudVendor));
 *         List<CloudVendor> cvs = cloudVendorService.getByVendorName("Amzon");
 *         System.out.println(cvs.isEmpty());
 *         assertAll(
 *              ()-> assertEquals("1",cvs.get(0).getVendorId()),
 *               ()->  assertEquals("Amzon",cvs.get(0).getVendorName()),
 *               ()-> assertEquals("falstin",cvs.get(0).getVendorAddress()),
 *                ()-> assertEquals("23423",cvs.get(0).getVendorPhoneNumber())
 *         );
 *     }
 *     @Test
 *     public void asyncMethod() throws InterruptedException {
 *
 *
 *
 *         // Create a mock of CloudVendorServiceImpl
 *         CloudVendorServiceImpl cloudVendorService = mock(CloudVendorServiceImpl.class);
 *         // Invoke the method
 *         cloudVendorService.asyncMethod();
 *         // Verify that the method was called within 100 milliseconds
 *         verify(cloudVendorService,timeout(100)).asyncMethod();
 *
 *     }
 *     @Test
 *     public void sendNotificationTest(){
 *         CloudVendorServiceImpl cloudVendorService1 = mock(CloudVendorServiceImpl.class);
 *         cloudVendorService1.sendNotification("hello","sa");
 *         // Use verify() to ensure the method was called with the right parameters
 *         verify(cloudVendorService1).sendNotification("hello","sa");
 *     }
 *     @Test
 *     public void orderOfInvocationTest() {
 *         // Generate a mock of AccountService
 *         CloudVendorServiceImpl mockService = mock(CloudVendorServiceImpl.class);
 *         // Simulate deposit and withdraw operations on the mock
 *         mockService.deposit(100);
 *         mockService.withdraw(50);
 *
 *         // Use inOrder to validate the order of invocation
 *         InOrder inOrder = inOrder(mockService);
 *         inOrder.verify(mockService).deposit(100);
 *         inOrder.verify(mockService).withdraw(50);
 *     }
 * }
 */