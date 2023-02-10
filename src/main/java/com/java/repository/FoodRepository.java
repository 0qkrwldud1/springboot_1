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
			name = 
	int addrtagListCount(@Param("tagValue")String foodAddr , String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_menu like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int menutagListCount(@Param("tagValue")String tagValue , @Param("tagType")String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_kind like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int kindtagListCount(@Param("tagValue")String tagValue , @Param("tagType")String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_area like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int areatagListCount(@Param("tagValue")String tagValue , @Param("tagType")String tagType);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_tag like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)")
	int tagtagListCount(@Param("tagValue")String tagValue , @Param("tagType")String tagType);
	
	@Query(name = "find_searchfooddto", nativeQuery = true)
	List<SearchFoodDto> addrtagList(@Param("tagValue")String foodAddr, @Param("startRow")int startRow, @Param("endRow")int endRow);
	
	@Query(nativeQuery = true ,
			value = "select * from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_menu like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)"
					+ "where r rnum >= = :startRow "
					+ "and rnum <= = :endRow")
	List<SearchFoodDto> menutagList(@Param("tagValue")String tagValue , @Param("tagType")String tagType, int startRow, int endRow);
	
	@Query(nativeQuery = true ,
			value = "select * from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_kind like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)"
					+ "where r rnum >= = :startRow "
					+ "and rnum <= = :endRow")
	List<SearchFoodDto> kindtagList(@Param("tagValue")String tagValue , @Param("tagType")String tagType, int startRow, int endRow);
	
	@Query(nativeQuery = true ,
			value = "select * from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_area like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)"
					+ "where r rnum >= = :startRow "
					+ "and rnum <= = :endRow")
	List<SearchFoodDto> areatagList(@Param("tagValue")String tagValue , @Param("tagType")String tagType, int startRow, int endRow);
	
	@Query(nativeQuery = true ,
			value = "select * from "
					+ "(select rownum rnum, a.*, image.* from"
					+ "(select from food where food_tag like %:tagValue%"
					+ "or like %:tagType%) orderby food_read desc)"
					+ "a, image where a.food_code = image.refer_code(+)"
					+ "where r rnum >= = :startRow "
					+ "and rnum <= = :endRow")
	List<SearchFoodDto> tagtagList(@Param("tagValue")String tagValue , @Param("tagType")String tagType, int startRow, int endRow);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from food where food_code in"
					+ "(select distinct food_code from"
					+ "(select food where food_name like %:keyword%"
					+ "union all"
					+ "select food where food_menu like %:keyword%"
					+ "union all"
					+ "select food where food_kind like %:keyword%"
					+ "union all"
					+ "select food where food_addr like %:keyword%"
					+ "union all"
					+ "and food_addr like %:addr%"
					+ "and food_kind not like %:kind%")
	int addrsearchCount(@Param("keyword")String keyword, @Param("addr")String[] addrArr, @Param("kind")String[] kindArr);
	
	@Query(nativeQuery = true ,
			value = "select count(*) from food where food_code in"
					+ "(select distinct food_code from"
					+ "(select food where food_name like %:keyword%"
					+ "union all"
					+ "select food where food_menu like %:keyword%"
					+ "union all"
					+ "select food where food_kind like %:keyword%"
					+ "union all"
					+ "select food where food_addr like %:keyword%"
					+ "union all"
					+ "and food_kind like %:kind% "
					+ "and food_kind not like %:addr%")
	int kindsearchCount(@Param("keyword")String keyword, @Param("addr")String[] addrArr, @Param("kind")String[] kindArr);
}
