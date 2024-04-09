package com.bookify.controller;

import com.bookify.dto.BookResponseDto;
import com.bookify.enitity.Book;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.service.impl.BookServiceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    @Autowired
    private BookServiceImpl bookService;


    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id)throws ProductNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping("/search/{query}")
    public List<BookResponseDto> search(@PathVariable String query) throws ProductNotFoundException{
        return bookService.searchProducts(query);
    }

    @GetMapping("/all")
    public List<Book> getAllProducts() {
        return bookService.getAllProducts();
    }


    @GetMapping("/name/{name}")
    public Book findByName(@PathVariable String name) throws ProductNotFoundException {
       return bookService.findBookByName(name);
    }

    @GetMapping("/name/edition")
    public Book SearchForAdd(@RequestParam String name,@RequestParam String edition){
        return bookService.SearchForAdd(name,edition);
    }

    @PostMapping("/")
    Book addProduct(@RequestBody  Book product){
        return bookService.addProduct(product);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateProduct(@RequestBody  Book book) throws ProductNotFoundException {
        bookService.updateProduct(book);
        return ResponseEntity.ok("updated successfully");
    }

    @DeleteMapping("/{name}/{edition}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name, @PathVariable String edition) {

        return ResponseEntity.ok(bookService.deleteProduct(name,edition));
    }

}
