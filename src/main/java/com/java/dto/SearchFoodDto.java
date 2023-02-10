package com.java.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @작성자 : 한문구
 * @작성일 : 2019. 12. 12.
 * @설명 : 음식점 DTO(VO) 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFoodDto {
	
	private int rnum;
	private String foodCode;
	private String foodAddr;
	private String foodArea;
	private String foodBreak;
	private Date foodDate;
	private String foodIntro;
	private String foodKind;
	private String foodMenu;
	private String foodName;
	private String foodPhone;
	private int foodRead;
	private String foodStatus;
	private String foodTime;
	private String member_code;
	private String imageCode;
	private String imageName;
	private String imagePath;
	private long imageSize;
	private String referCode;
	
	
	
}
