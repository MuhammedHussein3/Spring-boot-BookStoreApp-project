package com.bookify.service;


import com.bookify.dto.CategoryDto;
import com.bookify.enitity.Category;
import com.bookify.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {


    Category findCategoryById(Long id)throws CategoryNotFoundException;
    List<Category> searchCategoriesBYSearchQuery(String query);
    String addCategory(Category category);
    Category findCategoriesByName(String name);
    List<CategoryDto> getAllBooks(String name);
    List<Category> getAllCategories();
    void updateCategory(Category category) throws CategoryNotFoundException;
}
