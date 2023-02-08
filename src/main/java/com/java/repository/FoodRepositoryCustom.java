package com.java.repository;

import java.util.List;

import com.java.dto.SearchFoodDto;

public interface FoodRepositoryCustom {

	int searchCount(String keyword, String[] areaArr, String[] kindArr);
	
	List<SearchFoodDto> searchResult(String keyword, String orderType, String[] addrArr, String[] kindArr, int startRow,
			int endRow);
}
