package com.bookify.service.impl;

import com.bookify.dto.ReviewDto;
import com.bookify.dto.ReviewUpdateDto;
import com.bookify.enitity.Book;
import com.bookify.enitity.Review;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.exception.ReviewNotFoundException;
import com.bookify.mapper.Mapper;
import com.bookify.repository.ReviewRepo;
import com.bookify.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private Mapper mapper;
    public Review addReview(Review review) throws ProductNotFoundException {
        Book book = bookService.findBookByName(review.getBook().getName());
        book.addReview(review);
        return reviewRepo.save(review);
    }
    public List<ReviewDto> searchForBookReview(Long id) throws ProductNotFoundException {
        Book book = bookService.findById(id);
        if (book == null)
        {
            throw new ProductNotFoundException(String.format("Not found book with %s id",id));
        }
        List<ReviewDto> reviewDtos = new ArrayList<>();
        book.getReviews().forEach((review -> {

            reviewDtos.add( ReviewDto.builder().
                    username(review.getUser().getUsername())
                    .name(review.getUser().getName())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .build());
        }));
        return reviewDtos;
    }
    @Transactional
    @Override
    public String deleteReview(Long id) {
     reviewRepo.deleteReview(id);
        return "deleted successfully";
    }




    @Override
    public String updateReview(ReviewUpdateDto review) throws ReviewNotFoundException {
        Review review1 = reviewRepo.findById(review.getId()).get();
        if (review1 == null)
        {
            throw new ReviewNotFoundException(String.format("Not found review with %s ",review.getId()));
        }

        review1.setComment(review.getComment());
        review1.setRating(review.getRating());
        reviewRepo.save(review1);
        return "updated successfully";
    }
}
