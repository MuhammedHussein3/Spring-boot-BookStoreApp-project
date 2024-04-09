package com.bookify.controller;

import com.bookify.dto.CategoryDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Category;
import com.bookify.exception.CategoryNotFoundException;
import com.bookify.service.CategoryService;
import com.bookify.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/{id}")
    public Category findCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return categoryService.findCategoryById(id);
    }
    @GetMapping("/search/{query}")
    public List<Category> searchCategoriesBYSearchQuery(@PathVariable String query){
        return categoryService.searchCategoriesBYSearchQuery(query);
    }
    @PostMapping("/")
    public String addCategory(@RequestBody @Valid Category category) {
        return categoryService.addCategory(category);
    }
    @GetMapping("/books/{name}")
    public List<CategoryDto> getAllBooks(@PathVariable String name) {
        return categoryService.getAllBooks(name);
    }
    @GetMapping("/")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @PutMapping
    public ResponseEntity<?> updateCategory(Category category) throws CategoryNotFoundException {
        categoryService.updateCategory(category);
        return ResponseEntity.ok().build();
    }

}
