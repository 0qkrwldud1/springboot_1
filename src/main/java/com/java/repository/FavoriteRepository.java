package com.java.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.java.dto.SearchCouponDto;
import com.java.entity.Coupon;
import com.java.entity.Favorite;
import com.java.entity.Food;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	
	
    @Query(value = "select count(*) from favorite where member_code = :membercode and food_code = :foodcode"
    		,nativeQuery = true)
    int countBymembercodeAndfoodcode(@Param("membercode") String membercode, @Param("foodcode") String foodcode);
    
    
    @Query(value = "select count(*) from favorite where member_code = :membercode and food_code = :foodcode"
    		,nativeQuery = true)
    int countAfterBymembercodeAndfoodcode(@Param("membercode") String membercode, @Param("foodcode") String foodcode);
   
    
    @Query(value = "delete from favorite where food_code = :foodcode"
    		,nativeQuery = true)
    int deleteByfoodcode(@Param("foodcode")String foodcode);

   
    @Query(value = "insert into from favorite values (member_code = :membercode, food_code = :foodcode) "
    		, nativeQuery = true)
    int insertByfoodcode(@Param("membercode")String membercode, @Param("foodcode")String foodcode);
    
   
    Favorite findByfoodcode(String foodcode);
    
    
    
    
    
}
