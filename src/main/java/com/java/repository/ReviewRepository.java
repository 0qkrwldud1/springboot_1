package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.java.dto.SearchFoodDto;
import com.java.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {	



	


}
