package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	@Query("FROM Review WHERE food_code = :foodcode")
	Review getReviewScoreByfoodcode(String foodcode);
	
	

}
