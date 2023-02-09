package com.java.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.Favorite;
import com.java.repository.FavoriteRepository;
//import com.java.aop.JejuAspect;
//import com.java.favorite.dao.FavoriteDao;


@Component
public class FavoriteServiceImp implements FavoriteService {

	//@Autowired
	//private FavoriteDao favoriteDao;
	
	@Autowired
	private FavoriteRepository FavoriteRepository;
	
	@Override
	public int favorCheck(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		String membercode = (String) map.get("membercode");
		String foodcode = (String) map.get("foodcode");
		//JejuAspect.logger.info(JejuAspect.logMsg + memberCode + " || " + foodCode);
		
		//Map<String, String> map = new HashMap<String, String>();
		
		map.put("membercode", membercode);
		map.put("foodcode", foodcode);
		
		int check = FavoriteRepository.countBymembercodeAndfoodcode(membercode,foodcode);
		//JejuAspect.logger.info(JejuAspect.logMsg + "favorCheck : " + check);

		return check;
	}

	
	@Override
	public int favorSwitch(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		
		String membercode = (String) map.get("membercode");
		String foodcode = (String) map.get("foodcode");
		String favorStatus = (String) map.get("favorStatus");
		//JejuAspect.logger.info(JejuAspect.logMsg + memberCode + " || " + foodCode+ " || " + favorStatus);
		//int check = FavoriteRepository.favorSwitch(memberCode, foodCode, favorStatus);
		
		//Map<String, String> map = new HashMap<String, String>();
		
		map.put("membercode", membercode);
		map.put("foodcode", foodcode);
		System.out.println(favorStatus);
		
		int countBefore = FavoriteRepository.countBymembercodeAndfoodcode(membercode, foodcode);
		if (favorStatus.equals("on") && countBefore > 0) {
			FavoriteRepository.deleteByfoodcode(foodcode);
		} else if (favorStatus.equals("off") && countBefore == 0) {
			FavoriteRepository.insertByfoodcode(foodcode, membercode);
		}
		
		int countAfter = FavoriteRepository.countAfterBymembercodeAndfoodcode(membercode, foodcode, favorStatus);
		
		int check = countAfter;
		//int check = FavoriteRepository.favorSwitch(memberCode, foodCode, favorStatus);
		//JejuAspect.logger.info(JejuAspect.logMsg + "favorCheck : " + check);

		return check;
	}
	

}
