package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;

import com.java.dto.SearchFoodDto;
import com.java.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {	
=======

import com.java.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	@Query("FROM Review WHERE food_code = :foodcode")
	Review getReviewScoreByfoodcode(String foodcode);
	
	
>>>>>>> refs/remotes/origin/master

}