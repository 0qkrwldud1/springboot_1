package com.java.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.java.dto.SearchCouponDto;
import com.java.entity.Food;
import com.java.dto.SearchFoodDto;
import com.java.entity.Coupon;

public interface FoodRepository extends JpaRepository<Food, Long>
	{
	
	
	int countByFoodCode(String foodcode);
	
	@Query(name = "countByKeywordLike", nativeQuery = true)
	int couponCount(@Param("keyword") String keyword);
   
	@Query(name = "findByKeywordLike", nativeQuery = true)
	List<SearchCouponDto> couponList(@Param("keyword") String keyword);
	
	@Query(name = "count_addrsearchfooddto", nativeQuery = true)
	int addrtagListCount(@Param("tagValue")String foodAddr);
	
	@Query(name = "count_menusearchfooddto", nativeQuery = true)
	int menutagListCount(@Param("tagValue")String foodMenu);
	
	@Query(name = "count_kindsearchfooddto", nativeQuery = true)
	int kindtagListCount(@Param("tagValue")String foodKind);
	
	@Query(name = "count_areasearchfooddto", nativeQuery = true)
	int areatagListCount(@Param("tagValue")String foodArea);
	
	@Query(name = "find_tagsearchfooddto", nativeQuery = true)
	int tagtagListCount(@Param("tagValue")String foodTag);
	
	
	@Query(name = "find_addrsearchfooddto", nativeQuery = true)
	List<SearchFoodDto> addrtagList(@Param("tagValue")String foodAddr, @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	
	@Query(name = "find_menusearchfooddto", nativeQuery = true)
	List<SearchFoodDto> menutagList(@Param("tagValue")String foodMenu , @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	
	@Query(name = "find_kindsearchfooddto", nativeQuery = true)
	List<SearchFoodDto> kindtagList(@Param("tagValue")String foodKind , @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	@Query(name = "find_areasearchfooddto", nativeQuery = true)
	List<SearchFoodDto> areatagList(@Param("tagValue")String foodArea , @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	@Query(name = "find_tagsearchfooddto", nativeQuery = true)
	List<SearchFoodDto> tagtagList(@Param("tagValue")String foodTag , @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	
	
	
}
