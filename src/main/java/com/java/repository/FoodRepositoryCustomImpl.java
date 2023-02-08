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
				
		
		
		return null;
	}
}
