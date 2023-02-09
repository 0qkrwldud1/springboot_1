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
	
	
	// couponList, couponrepo
	@Query(nativeQuery = true ,
			value = "select * from coupon as c "
                    + "join food as f on c.food_code = f.food_code "
                    + "AND coupon_name like %:keyword%"
    				+ "OR food_name like %:keyword%"
    				+ "OR food_kind like %:keyword%"
    				+ "OR food_menu like %:keyword%"
    				+ "OR food_tag like %:keyword%")
	List<SearchCouponDto> findByKeywordLike(@Param("keyword") String keyword);
	
	// countQuery 쿼리전체개수세기 페이징처리용
	@Query(nativeQuery = true ,
			value = "select count(*) from coupon as c "
                    + "join food as f on c.food_code = f.food_code "
                    + "AND coupon_name like %:keyword%"
    				+ "OR food_name like %:keyword%"
    				+ "OR food_kind like %:keyword%"
    				+ "OR food_menu like %:keyword%"
    				+ "OR food_tag like %:keyword%")
	int countByKeywordLike(@Param("keyword") String keyword);
   

	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_addr like %:tagValue%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int addrtagListCount(@Param("tagValue")String tagValue , String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_menu like %:tagValue%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int menutagListCount(@Param("tagValue")String tagValue , String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_kind like %:tagValue%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int kindtagListCount(@Param("tagValue")String tagValue , String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_area like %:tagValue%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int areatagListCount(@Param("tagValue")String tagValue , String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_tag like %:tagValue%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int tagtagListCount(@Param("tagValue")String tagValue , String tagType);
	
	List<SearchFoodDto> tagList(String tagValue, String tagType, int startRow, int endRow);

}
