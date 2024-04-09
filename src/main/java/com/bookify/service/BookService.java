package com.bookify.service;



import com.bookify.dto.BookResponseDto;

import com.bookify.enitity.Book;
import com.bookify.enitity.Book;
import com.bookify.exception.ProductNotFoundException;

import java.util.List;

public interface BookService {

     Book findById(Long id) throws ProductNotFoundException;
     List<BookResponseDto> searchProducts(String q) throws ProductNotFoundException;
     List<Book> getAllProducts();
     Book SearchForAdd(String name,String edition);
     int updatedQuantityForBook(String name,String edition,int q);
     Book findBookByName(String name) throws ProductNotFoundException;
     Book addProduct(Book product);
     String updateProduct(Book product) throws ProductNotFoundException;
     String deleteProduct(String name,String edition);

}
