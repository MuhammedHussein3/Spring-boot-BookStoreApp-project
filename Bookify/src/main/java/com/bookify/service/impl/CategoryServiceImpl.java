package com.bookify.service.impl;

import com.bookify.dto.CategoryDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Category;
import com.bookify.exception.CategoryNotFoundException;
import com.bookify.repository.CategoryRepo;
import com.bookify.service.CategoryService;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    @Cacheable(cacheNames = "findCategoryById",key = "#id")
    public Category findCategoryById(Long id) throws CategoryNotFoundException{
      Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent())
        {
            return category.get();
        }
        throw new CategoryNotFoundException(String.format("Not found Category with %s id",id));
    }
    @Cacheable(cacheNames = "searchCategoriesBYSearchQuery",key = "#query")
    @Override
    public List<Category> searchCategoriesBYSearchQuery(String query) {
        return categoryRepo.searchCategoriesBYSearchQuery(query);
    }

    @CacheEvict(value = {"getAllBooks","searchCategoriesBYSearchQuery","getAllCategories","findCategoryById"}, allEntries = true)
    @Override
    public String addCategory(Category category) {
        categoryRepo.save(category);
        return "add category successfully";
    }
    @Override
    public Category findCategoriesByName(String name) {
        return categoryRepo.findCategoriesByName(name);
    }

    @Cacheable(cacheNames = "getAllBooks",key = "#name")
    @Override
    public List<CategoryDto> getAllBooks(String name) {
        List<Book> books = findCategoriesByName(name).getBooks();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        books.forEach(b->{
            CategoryDto categoryDto = CategoryDto.builder()
                    .bookName(b.getName())
                    .edition(b.getEdition())
                    .authorName(b.getAuthorName())
                    .publishBook(b.getPublishBook()).build();
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

    @Cacheable(cacheNames = "getAllCategories",key = "#root.methodName")
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
    @Override
    public void updateCategory(Category category) throws CategoryNotFoundException {
        Category caUpdate = findCategoryById(category.getCategoryId());
        if (caUpdate==null){
            throw new CategoryNotFoundException("Not found Category with id "+category.getCategoryId());
        }
        categoryRepo.save(caUpdate);
    }



}
