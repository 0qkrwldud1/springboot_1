package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.java.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>, 
	QuerydslPredicateExecutor<Food>, FoodRepositoryCustom{
	
	

}
