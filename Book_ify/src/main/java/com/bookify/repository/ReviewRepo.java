package com.bookify.repository;

import com.bookify.enitity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

    @Modifying
    @Query("""
DELETE Review r
WHERE r.id = :id
""")
    void deleteReview(Long id);
}
