package com.bookify.controller;

import com.bookify.dto.ReviewDto;
import com.bookify.dto.ReviewUpdateDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Review;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.exception.ReviewNotFoundException;
import com.bookify.repository.ReviewRepo;
import com.bookify.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewService;
    @PostMapping("/")
    Review addReview(@RequestBody @Valid Review review) throws ProductNotFoundException {
       return reviewService.addReview(review);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDto>> searchForBookReview(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(reviewService.searchForBookReview(id));
    }
    @DeleteMapping("/")
    public String deleteReview(@RequestParam Long id) {
        reviewService.deleteReview(id);
        return "deleted successfully";
    }
    @PutMapping("/")
    public String updateReview(@RequestBody @Valid ReviewUpdateDto review) throws ReviewNotFoundException {
        return reviewService.updateReview(review);
    }
}
