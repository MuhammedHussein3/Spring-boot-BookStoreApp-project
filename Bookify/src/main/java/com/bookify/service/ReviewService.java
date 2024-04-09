package com.bookify.service;

import com.bookify.dto.ReviewDto;
import com.bookify.dto.ReviewUpdateDto;
import com.bookify.enitity.Review;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.exception.ReviewNotFoundException;

import java.util.List;


public interface ReviewService {

    Review addReview(Review review) throws ProductNotFoundException;
    List<ReviewDto> searchForBookReview(Long id) throws ProductNotFoundException;
    String deleteReview(Long id);
    String updateReview(ReviewUpdateDto review) throws ReviewNotFoundException;
}
