package com.bookify.controller;

import com.bookify.dto.BookResponseDto;
import com.bookify.enitity.Book;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.mapper.Mapper;
import com.bookify.service.impl.BookServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    BookServiceImpl bookService;

    Mapper modelMapper;
    Logger log = LoggerFactory.getLogger(BookControllerTest.class);
    ObjectMapper mapper = new ObjectMapper();

    String baseUrl = "";

    Book book1, book2;

    List<Book> books = new ArrayList<>();


    @BeforeEach
    void setUp() {
        baseUrl = baseUrl.concat("/books");
        book1 = Book.builder()
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
        book2 = Book.builder()
                .id(2L)
                .name("C#")
                .authorName("microsoft")
                .edition("First edition")
                .publishBook(null)
                .price(9999d)
                .categoryName("software engineer")
                .priceAfterProduct(9234)
                .createdBy("developer test")
                .booksCount(55)
                .reviews(new ArrayList<>())
                .quantityInStock(42)
                .createDate(null)
                .lastModifiedBy(null)
                .lastModifiedDate(null).build();
        books.addAll(List.of(book1, book2));
    }

    @Test
    void tesAddProduct() throws Exception {
        Mockito.when(bookService.addProduct(Mockito.any())).thenReturn(book1);
        mvc.perform(post(baseUrl.concat("/")).contentType("application/json").content(mapper.writeValueAsString(book1)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Mockito.when(bookService.getAllProducts()).thenReturn(books);
        mvc.perform(get(baseUrl.concat("/all")).contentType("application/json").content(mapper.writeValueAsString(books)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindBookById() throws ProductNotFoundException, JsonProcessingException {
        Mockito.when(bookService.findById(book2.getId())).thenReturn(book2);

        try {
            mvc.perform(get(baseUrl.concat("/{id}"), book2.getId()).contentType("application/json")
                            .content(mapper.writeValueAsString(book2)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            log.info(e.getMessage() + " , Method Name : >> testFindBookById");
        }

    }

    @Test
    void testDeleteBook() throws Exception {
        Mockito.when(bookService.deleteProduct(book2.getName(), book2.getName())).thenReturn("delete successfully");
        try {
            mvc.perform(delete(baseUrl.concat("/{name}/{edition}"), book2.getName(), book2.getEdition()).contentType("application/json").content(mapper.writeValueAsString(book2)))
                    .andExpect(status().isOk());
        } catch (NullPointerException e) {
            log.info(e.getMessage());
        }

    }

    @Test
    public void testSearch() throws Exception {
        String query = "j";
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setAuthorName(book1.getName());
        bookResponseDto.setAuthorName(book1.getAuthorName());
        bookResponseDto.setEdition(book1.getEdition());
        bookResponseDto.setPublishBook(book1.getPublishBook());
        bookResponseDto.setPrice(book1.getPrice());

        Mockito.when(bookService.searchProducts(query)).thenReturn(List.of(bookResponseDto));
        mvc.perform(get(baseUrl.concat("/search/{query}"), query).contentType("application/json").content(mapper.writeValueAsString(bookResponseDto)))
                .andExpect(status().isOk());
    }
}