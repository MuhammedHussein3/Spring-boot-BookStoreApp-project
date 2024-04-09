package com.bookify.service.impl;


import com.bookify.Projctions.BookProjection;
import com.bookify.dto.BookResponseDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Category;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.mapper.Mapper;
import com.bookify.repository.BookRepo;
import com.bookify.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private Mapper mapper;


    public Book findById(Long id) throws ProductNotFoundException {
        Optional<Book> product =  bookRepo.findById(id);
        if (product.isPresent())
            return product.get();

        throw new ProductNotFoundException(String.format("Not found book with %s id ",id));
    }
    @Override
    public List<BookResponseDto> searchProducts(String q) throws ProductNotFoundException {

       List<BookProjection> bookProjections = bookRepo.searchProducts(q);
       if (!bookProjections.isEmpty())
       {
           List<BookResponseDto> productResponseDtos = new ArrayList<>();
           bookProjections.forEach((p)->{
               productResponseDtos.add(mapper.from(p));
           });
           return productResponseDtos;
       }
        throw new ProductNotFoundException(String.format("Not found any book with %s name",q));
    }

    @Override
    public List<Book> getAllProducts() {
        return bookRepo.findAll();
    }

    @Override
    public Book findBookByName(String name) throws ProductNotFoundException {
        Book product = bookRepo.findProductByName(name);
        if (product==null)
        {
            throw new ProductNotFoundException(String.format("Not found book with %s name",name));
        }
        return product;
    }

    public int updatedQuantityForBook(String name,String edition,int q){
        return bookRepo.plusSameBook(name,edition,q);
    }
    @Override
    public Book SearchForAdd(String name,String edition){
        return bookRepo.SearchForAdd(name,edition);
    }

    @Transactional
    @Override
    public Book addProduct(Book entity){

        Category category = categoryService.findCategoriesByName(entity.getCategoryName());
        category.addBook(entity);
        Book book = SearchForAdd(entity.getName(),entity.getEdition());
          if (book==null) {
              entity.setPublishBook(LocalDateTime.now());
              return bookRepo.save(entity);
          }
        updatedQuantityForBook(entity.getName(),entity.getEdition(),book.getQuantityInStock()+entity.getQuantityInStock());
          return entity;

    }


    @Override
    public String updateProduct(Book product) throws ProductNotFoundException {
        Book productToUpdate = findBookByName(product.getName());
        if (productToUpdate==null)
        {
            throw new ProductNotFoundException(String.format("Not found exception with %s name",product.getName()));
        }
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());

        bookRepo.save(productToUpdate);

        return "Updated successfully";
    }

    @Override
    public String deleteProduct(String name, String edition) {
        Book product = bookRepo.SearchForAdd(name,edition);

       if (product==null)
           return "UnSuccessfully delete";
        bookRepo.deleteProduct(name,edition);
       return "Successfully delete";
    }


}
