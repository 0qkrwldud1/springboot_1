package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

//import com.java.aop.JejuAspect;
import com.java.dto.SearchCouponDto;
import com.java.dto.SearchFoodDto;
import com.java.repository.CouponRepository;
//import com.java.dao.SearchDao;
import com.java.repository.FoodRepository;
import com.java.repository.ImageRepository;
import com.java.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional

public class SearchServiceImp implements SearchService {
	
	//@Autowired
	//private SearchDao searchDao;
	
	@Autowired
	private final CouponRepository couponRepository;
	
	@Autowired
	private final FoodRepository foodRepository;
	
	@Autowired
	private final ReviewRepository reviewRepository;
	
	@Autowired
	private final ImageRepository imageRepository;

	
	//키워드 검색
	public void searchKeyword(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String keyword =  (String) map.get("keyword");
		//JejuAspect.logger.info(JejuAspect.logMsg + keyword);
		if (keyword == null) {
			keyword = "";
		}
		
		// 쿠폰 수

		int couponCount = foodRepository.countByKeywordLike(keyword);
		//int couponCount = searchDao.couponCount(keyword);
		//JejuAspect.logger.info(JejuAspect.logMsg + couponCount);
		
		List<SearchCouponDto> couponList = new ArrayList<SearchCouponDto>();
		if (couponCount > 0) {
			couponList = foodRepository.findByKeywordLike(keyword);

			//couponList = searchDao.couponList(keyword);
		}
		//JejuAspect.logger.info(JejuAspect.logMsg + couponList.size());

		mav.addObject("couponCount", couponCount);
		mav.addObject("couponList", couponList);
		
		mav.setViewName("search/search.tiles");
	}
	
	
	// 음식점 목록
	@Override
	public String foodList(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		
		String tagValue = (String) map.get("tagValue");
		String tagType = (String) map.get("tagType");
		String pageNumber = (String) map.get("pageNumber");
		//JejuAspect.logger.info(JejuAspect.logMsg + tagValue + " || " + tagType);
		
		
		int count = 0;
		
		if (tagValue.equals("adrr") || tagType.equals("adrr")) {
			count = foodRepository.addrtagListCount(tagValue, tagType);
		} else if ( tagValue.equals("menu") || tagType.equals("menu") ) {
			count = foodRepository.menutagListCount(tagValue, tagType);
		} else if ( tagValue.equals("kind") || tagType.equals("kind") ) {
			count = foodRepository.kindtagListCount(tagValue, tagType);
		} else if ( tagValue.equals("area") || tagType.equals("area") ) {
			count = foodRepository.areatagListCount(tagValue, tagType);
		} else if ( tagValue.equals("tag") || tagType.equals("tag") ) {
			count = foodRepository.tagtagListCount(tagValue, tagType);
		} 
		
		//Map<String, Object> map1 = new HashMap<String, Object>();
		map.put("tagValue", tagValue);
		map.put("tagType", tagType);
		
		//JejuAspect.logger.info(JejuAspect.logMsg + "tagListCount: " + count);
		
		if (pageNumber == null)
			pageNumber = "1";
		int currentPage = Integer.parseInt(pageNumber);
		int scrollSize = 12;
		int startRow = (currentPage - 1) * scrollSize + 1;
		int endRow = currentPage * scrollSize;
		if (endRow > count) {
			endRow = count;
		}

		

		List<SearchFoodDto> foodList = new ArrayList<SearchFoodDto>();
		
		if (count > 0 && count >= startRow) {
			if (tagValue.equals("adrr")) {
			foodList = foodRepository.addrtagList(tagValue, startRow, endRow);
			//JejuAspect.logger.info(JejuAspect.logMsg + foodList.size());
			} else if ( tagValue.equals("menu") || tagType.equals("menu") ) {
				foodList = foodRepository.menutagList(tagValue, tagType, startRow, endRow);
			} else if ( tagValue.equals("kind") || tagType.equals("kind") ) {
				foodList = foodRepository.kindtagList(tagValue, tagType, startRow, endRow);
			} else if ( tagValue.equals("area") || tagType.equals("area") ) {
				foodList = foodRepository.areatagList(tagValue, tagType, startRow, endRow);
			} else if ( tagValue.equals("tag") || tagType.equals("tag") ) {
				foodList = foodRepository.tagtagList(tagValue, tagType, startRow, endRow);
			} 
		}
			
		map.put("tagValue", tagValue);
		map.put("tagType", tagType);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		
		JSONArray arr = new JSONArray();
		for(SearchFoodDto sFoodDto : foodList) {
			HashMap<String, Object> jMap = new HashMap<String, Object>();
			jMap.put("foodCode", sFoodDto.getFoodCode());
			jMap.put("foodName", sFoodDto.getFoodName());
			jMap.put("foodMenu", sFoodDto.getFoodMenu());
			jMap.put("foodKind", sFoodDto.getFoodKind());
			jMap.put("foodAddr", sFoodDto.getFoodAddr());
			jMap.put("foodArea", sFoodDto.getFoodArea());
			jMap.put("imageName", sFoodDto.getImageName());
			jMap.put("imagePath", sFoodDto.getImagePath());
			arr.add(jMap);
//			JejuAspect.logger.info(JejuAspect.logMsg + jMap.toString());
		}
		String jsonText = JSONValue.toJSONString(arr);
		//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);
		
		return jsonText;
	}
	
	/*
	@Override
	public String keywordAuto(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String keyword = (String) map.get("keyword");
		//JejuAspect.logger.info(JejuAspect.logMsg + "keyword: " + keyword);
		
		List<SearchFoodDto> keywordList = new ArrayList<SearchFoodDto>();
		keywordList = searchDao.keywordList(keyword);
		//JejuAspect.logger.info(JejuAspect.logMsg + keywordList.size());

		JSONArray arr = new JSONArray();
		for(SearchFoodDto sFoodDto : keywordList) {
			arr.add(sFoodDto.getFoodName());
		}
		String jsonText = JSONValue.toJSONString(arr);
		//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);
		
		return jsonText;
	}
	*/

	@Override
	public int searchCount(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String keyword = (String) map.get("keyword");
		String addrType = (String) map.get("addrType");
		String kindType = (String) map.get("kindType");
		
		
		
		String[] addrArr = null;
		String[] kindArr = null;
		if (addrType != null) {
			addrArr = addrType.split(",");
		}
		if (kindType != null) {
			kindArr = kindType.split(",");
		}
		
		
		//JejuAspect.logger.info(JejuAspect.logMsg + "arrLength : " + kindArr.length);
		
		int searchCount = 0;
		
		if (addrArr[0] != null) {
			searchCount = foodRepository.addrsearchCount(keyword, addrArr, kindArr);
		} else if (addrArr[0] != null) {
			searchCount = foodRepository.kindsearchCount(keyword, addrArr, kindArr);
		}
		
		map.put("keyword", keyword);
		map.put("addrArr", addrArr);
		map.put("kindArr", kindArr);
		//int searchCount = searchDao.searchCount(keyword, addrArr, kindArr);
		return searchCount;
	}

	
	//@Override
	public String searchResult(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String keyword = (String) map.get("keyword");
		String currentPage = (String) map.get("currentPage");
		String orderType = (String) map.get("orderType");
		String addrType = (String) map.get("addrType");
		String kindType = (String) map.get("kindType");
		//JejuAspect.logger.info(JejuAspect.logMsg + keyword + " || " + currentPage + " || " + orderType + " || " + addrType + " || " + kindType);
		
		// 검색 조건 Arr 변환
		String[] addrArr = null;
		String[] kindArr = null;
		if (addrType != null) {
			addrArr = addrType.split(",");
		}
		if (kindType != null) {
			kindArr = kindType.split(",");
		}
		
		int pageNumber = Integer.parseInt(currentPage); 		// 요청페이지 번호 (def.1)
		int boardSize = 10; 									// 페이지당 출력할 게시물 수
		int startRow = boardSize * (pageNumber - 1) + 1;		// 시작번호
		int endRow = boardSize * pageNumber;
		
		int searchCount = foodRepository.searchCount(keyword, addrArr, kindArr);
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		
		map1.put("keyword", keyword);
		map1.put("addrArr", addrArr);
		map1.put("kindArr", kindArr);
		
		List<SearchFoodDto> searchResultList = new ArrayList<SearchFoodDto>();
		if (searchCount > 0) {
			searchResultList = foodRepository.searchResult(keyword, orderType, addrArr, kindArr, startRow, endRow);
		}
		
		//JejuAspect.logger.info(JejuAspect.logMsg + searchCount + " / " + searchResultList.size());

		JSONArray arr = new JSONArray();
		for(SearchFoodDto sFoodDto : searchResultList) {
			HashMap<String, Object> jMap = new HashMap<String, Object>();
			jMap.put("foodCode", sFoodDto.getFoodCode());
			jMap.put("foodName", sFoodDto.getFoodName());
			jMap.put("foodMenu", sFoodDto.getFoodMenu());
			jMap.put("foodKind", sFoodDto.getFoodKind());
			jMap.put("foodAddr", sFoodDto.getFoodAddr());
			jMap.put("foodArea", sFoodDto.getFoodArea());
			jMap.put("foodRead", sFoodDto.getFoodRead());
			jMap.put("reviewCount", sFoodDto.getReviewCount());
			jMap.put("reviewScore", sFoodDto.getReviewScore());
			jMap.put("imageName", sFoodDto.getImageName());
			jMap.put("imagePath", sFoodDto.getImagePath());
			arr.add(jMap);
//			JejuAspect.logger.info(JejuAspect.logMsg + jMap.toString());
		}
		String jsonText = JSONValue.toJSONString(arr);
		//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);
		
		return jsonText;
	}

	/*
	@Override
	public String popularList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		List<SearchFoodDto> popularList = new ArrayList<SearchFoodDto>();
		popularList = searchDao.popularList();
		
		//JejuAspect.logger.info(JejuAspect.logMsg + "popularList : " + popularList.size());

		JSONArray arr = new JSONArray();
		for(SearchFoodDto sFoodDto : popularList) {
			HashMap<String, Object> jMap = new HashMap<String, Object>();
			jMap.put("foodCode", sFoodDto.getFoodCode());
			jMap.put("foodName", sFoodDto.getFoodName());
			jMap.put("foodMenu", sFoodDto.getFoodMenu());
			jMap.put("foodKind", sFoodDto.getFoodKind());
			jMap.put("foodAddr", sFoodDto.getFoodAddr());
			jMap.put("foodArea", sFoodDto.getFoodArea());
			jMap.put("foodRead", sFoodDto.getFoodRead());
			jMap.put("reviewCount", sFoodDto.getReviewCount());
			jMap.put("reviewScore", sFoodDto.getReviewScore());
			jMap.put("imageName", sFoodDto.getImageName());
			jMap.put("imagePath", sFoodDto.getImagePath());
			arr.add(jMap);
//			JejuAspect.logger.info(JejuAspect.logMsg + jMap.toString());
		}
		String jsonText = JSONValue.toJSONString(arr);
		//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);
		
		return jsonText;
	}

	@Override
	public String countCont(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String countFood = Integer.toString(searchDao.countFood());
		String countReview = Integer.toString(searchDao.countReview());
		
		//JejuAspect.logger.info(JejuAspect.logMsg + "countFood : " + countFood + "countReview : " + countReview);

		HashMap<String, String> jMap = new HashMap<String, String>();
		jMap.put("countFood", countFood);
		jMap.put("countReview", countReview);

		String jsonText = JSONValue.toJSONString(jMap);
		//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);
		
		return jsonText;
	}
	
	*/
}
