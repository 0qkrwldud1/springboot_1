<<<<<<< HEAD
 package com.java.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.entity.Food;
import com.java.entity.QFood;
import com.java.dto.SearchFoodDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodRepositoryCustomImpl implements FoodRepositoryCustom {
	
	 private final EntityManager em;
	 private final JPAQueryFactory queryFactory;

	 public FoodRepositoryCustomImpl(EntityManager em) {
	      this.em = em;
	      this.queryFactory = new JPAQueryFactory(em);
	 }
	
	 public int searchCount(String keyword, String[] areaArr, String[] kindArr) {
		 QFood food = QFood.food;
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		// map = queryFactory.select(food).
		 
		return 0;
	}
	
	public List<SearchFoodDto> searchResult(String keyword, String orderType, String[] addrArr, String[] kindArr,
			int startRow, int endRow) {
				
=======
package com.java.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.java.entity.Food;
import com.java.entity.QFood;
import com.java.dto.SearchFoodDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class FoodRepositoryCustomImpl implements FoodRepositoryCustom {

	private JPAQueryFactory queryFactory;
	
	public FoodRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
	
	public int searchCount(String keyword, String[] areaArr, String[] kindArr) {
		
				
		
		return 0;
		
	}
	
	
	public List<SearchFoodDto> searchResult(String keyword, String orderType, String[] addrArr, String[] kindArr,
			int startRow, int endRow) {
				
		
		
>>>>>>> refs/remotes/origin/master
		return null;
	}
}
