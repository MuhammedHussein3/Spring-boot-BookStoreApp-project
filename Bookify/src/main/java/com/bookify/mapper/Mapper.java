package com.bookify.mapper;


import com.bookify.Projctions.BookProjection;
import com.bookify.dto.BookResponseDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private ModelMapper modelMapper;


    public BookResponseDto from(BookProjection productProjection){
        return modelMapper.map(productProjection,BookResponseDto.class);
    }
    public Book from(Book book){
        return modelMapper.map(book,Book.class);
    }
    public Category from(Category category){
        return modelMapper.map(category,Category.class);
    }

}
