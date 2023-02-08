package com.java.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.java.dto.SearchCouponDto;
import com.java.entity.Food;
import com.java.dto.SearchFoodDto;
import com.java.entity.Coupon;

public interface FoodRepository extends JpaRepository<Food, Long>, 
	QuerydslPredicateExecutor<Food>, FoodRepositoryCustom{
	
	
	// couponList, couponrepo
	@Query(nativeQuery = true ,
			value = "select * from " 
					+ "coupon as c "
                    + "join food as f"
                    + "on c.food_code = f.food_code "
                    + "AND coupon_name like %:keyword%"
    				+ "OR food_name like %:keyword%"
    				+ "OR food_kind like %:keyword%"
    				+ "OR food_menu like %:keyword%"
    				+ "OR food_tag like %:keyword%")
	List<SearchCouponDto> findByKeywordLike(@Param("keyword") String keyword);
	
	// countQuery 쿼리전체개수세기 페이징처리용
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "coupon as c "
                    + "join food as f"
                    + "on c.food_code = f.food_code "
                    + "AND coupon_name like %:keyword%"
    				+ "OR food_name like %:keyword%"
    				+ "OR food_kind like %:keyword%"
    				+ "OR food_menu like %:keyword%"
    				+ "OR food_tag like %:keyword%")
	int countByKeywordLike(@Param("keyword") String keyword);
   
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.java.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>, 
	QuerydslPredicateExecutor<Food>, FoodRepositoryCustom{
	
	
>>>>>>> refs/remotes/origin/master

}
