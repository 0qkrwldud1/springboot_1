package com.java.entity;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.dto.SearchFoodDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NamedNativeQuery(				
		name = "countByKeywordLike",			
		query =			
		"	select  "	+ 
		"	(	select count (*) from " +			
		"  	coupon_code as  couponCode, 	" +
		"	coupon_costori as couponCostori, 	" +
		"	coupon_costsale as  couponCostsale, 	" +
		"	coupon_enddate as  couponEnddate, 	" +
		"	coupon_intro as  couponIntro, 	" +
		"	coupon_name as  couponName, 	" +
		"	coupon_salerate as  couponSalerate, 	" +
		"	coupon_startdate as  couponStartdate, 	" +
		"	coupon_status as  couponStatus, " +
		"	food.food_code as foodCode,	" +
		"	food_addr as foodAddr, 	" +
		"	food_area as foodArea, 	" +
		"	food_date as  foodDate, 	" +
		"	food_intro as foodIntro, 	" +
		"	food_kind as foodKind, 	" +
		"	food_menu as foodMenu, 	" +
		"	food_name as foodName, 	" +
		"	food_phone as foodPhone, 	" +
		"	food_read as foodRead, 	" +
		"	food_time as foodTime, 	" +
		"	member_code as memberCode 	" +
		"	from coupon, food	"+ 
		"	WHERE coupon.food_code = food.food_code	" +  
		"	and ( coupon_name like %:keyword%	"	+ 
		"	or food_name like %:keyword%	"	+ 
		"	or food_kind like %:keyword%	"	+ 
		"	or food_menu like %:keyword%))",				
	resultSetMapping = "searchcouponcountdto"				
	)		

@NamedNativeQuery(				
		name = "countByKeywordLike",			
		query =			
				"	select  "	+ 			
				"  	coupon_code as  couponCode, 	" +
				"	coupon_costori as couponCostori, 	" +
				"	coupon_costsale as  couponCostsale, 	" +
				"	coupon_enddate as  couponEnddate, 	" +
				"	coupon_intro as  couponIntro, 	" +
				"	coupon_name as  couponName, 	" +
				"	coupon_salerate as  couponSalerate, 	" +
				"	coupon_startdate as  couponStartdate, 	" +
				"	coupon_status as  couponStatus, " +
				"	food.food_code as foodCode,	" +
				"	food_addr as foodAddr, 	" +
				"	food_area as foodArea, 	" +
				"	food_date as  foodDate, 	" +
				"	food_intro as foodIntro, 	" +
				"	food_kind as foodKind, 	" +
				"	food_menu as foodMenu, 	" +
				"	food_name as foodName, 	" +
				"	food_phone as foodPhone, 	" +
				"	food_read as foodRead, 	" +
				"	food_time as foodTime, 	" +
				"	member_code as memberCode, 	" +
				"	image_code as imageCode, 	 	" +
				"	image_name as imageName, 	" +
				"	image_path as imagePath,  	" +
				"	image_size as imageSize, 	" +
				"	refer_code as referCode	 	" +
				"	from coupon, food, image	"+ 
				"	WHERE coupon.food_code = food.food_code	" + 
				"	AND coupon.coupon_code = image.refer_code(+)	"+ 
				"	and ( coupon_name like %:keyword%	"	+ 
				"	or food_name like %:keyword%	"	+ 
				"	or food_kind like %:keyword%	"	+ 
				"	or food_menu like %:keyword%	"	+ 
				"	) order by coupon_salerate desc",				
	resultSetMapping = "searchcouponlistdto"				
	)		


@NamedNativeQuery(				
		name = "countByKeywordLike",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_addr like %:foodAddr% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)	


@NamedNativeQuery(				
		name = "couponList",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_addr like %:foodAddr% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)		

@NamedNativeQuery(				
		name = "find_menusearchfooddto",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_menu like %:foodMenu% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)		

@NamedNativeQuery(				
		name = "find_kindsearchfooddto",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_kind like %:foodKind% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)			

@NamedNativeQuery(				
		name = "find_areasearchfooddto",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_area like %:foodArea% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)			

@NamedNativeQuery(				
		name = "find_tagsearchfooddto",			
		query =			
		"select  " +			
	"	rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow)",				
	resultSetMapping = "searchfooddto"				
	)	

@NamedNativeQuery(				
		name = "count_addrsearchfooddto",			
		query =			
		"select  count(*) from " +			
	"	(select rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow))",				
	resultSetMapping = "searchfooddto"				
	)	

@NamedNativeQuery(				
		name = "count_menusearchfooddto",			
		query =			
		"select  count(*) from " +			
	"	(select rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow))",				
	resultSetMapping = "searchfooddto"				
	)	

@NamedNativeQuery(				
		name = "count_kindsearchfooddto",			
		query =			
		"select  count(*) from " +			
	"	(select rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow))",				
	resultSetMapping = "searchfooddto"				
	)	

@NamedNativeQuery(				
		name = "count_areasearchfooddto",			
		query =			
		"select  count(*) from " +			
	"	(select rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow))",				
	resultSetMapping = "searchfooddto"				
	)	

@NamedNativeQuery(				
		name = "count_tagsearchfooddto",			
		query =			
		"select  count(*) from " +			
	"	(select rnum AS rnum,	" +		
	"	food_code AS foodCode,	" +		
	"	food_addr AS foodAddr, 	" +		
	"	food_area AS foodArea, 	" +		
	"	food_break AS foodBreak, 	" +		
	"	food_date AS foodDate, 	" +		
	"	food_intro AS foodIntro, 	" +		
	"	food_kind AS foodKind, 	" +		
	"	food_menu AS foodMenu, 	" +		
	"	food_name AS foodName, 	" +		
	"	food_phone AS foodPhone, 	" +		
	"	food_read AS foodRead, 	" +		
	"	food_status AS foodStatus, 	" +		
	"	food_time AS foodTime, 	" +		
	"	member_code AS memberCode, 	" +		
	"	image_code AS imageCode, 	" +		
	"	image_name AS imageName, 	" +		
	"	image_path AS imagePath, 	" +		
	"	image_size AS imageSize, 	" +		
	"	refer_code AS referCode	" +		
	" from (select rownum rnum, a.*, image.* from " +				
	"(select from food where food_tag like %:foodTag% " +				
	"orderby food_read desc) "+				
	"a, image where a.food_code = image.refer_code(+) " +				
	"where r rnum >= = :startRow "+				
	"and rnum <= = :endRow))",				
	resultSetMapping = "searchfooddto"				
	)	

					
	@SqlResultSetMapping(				
	name = "searchfooddto",				
	classes = @ConstructorResult(				
	targetClass = SearchFoodDto.class,				
	columns = {				
	@ColumnResult(name = "rnum", type = int .class),				
	@ColumnResult(name = "foodCode", type = String.class),				
	@ColumnResult(name = "foodAddr", type = String.class),				
	@ColumnResult(name = "foodArea", type = String.class),				
	@ColumnResult(name = "foodBreak", type = String.class),				
	@ColumnResult(name = "foodDate", type = Date.class),				
	@ColumnResult(name = "foodIntro", type = String.class),				
	@ColumnResult(name = "foodKind", type = String.class),				
	@ColumnResult(name = "foodMenu", type = String.class),				
	@ColumnResult(name = "foodName", type = String.class),				
	@ColumnResult(name = "foodPhone", type = String.class),				
	@ColumnResult(name = "foodRead", type = int .class),				
	@ColumnResult(name = "foodStatus", type = String.class),				
	@ColumnResult(name = "foodTime", type = String.class),				
	@ColumnResult(name = "memberCode", type = String.class),				
	@ColumnResult(name = "imageCode", type = String.class),				
	@ColumnResult(name = "imageName", type = String.class),				
	@ColumnResult(name = "imagePath", type = String.class),				
	@ColumnResult(name = "imageSize", type = long.class),				
	@ColumnResult(name = "referCode", type = String.class)				
	}				
	)				
	)		


	@SqlResultSetMapping(				
	name = "searchcouponlistdto",				
	classes = @ConstructorResult(				
	targetClass = SearchFoodDto.class,				
	columns = {				
	@ColumnResult(name = "couponCode", type = String .class),				
	@ColumnResult(name = "couponCostori", type = String.class),				
	@ColumnResult(name = "couponCostsale", type = int.class),				
	@ColumnResult(name = "couponEnddate", type = String.class),				
	@ColumnResult(name = "couponIntro", type = String.class),				
	@ColumnResult(name = "couponName", type = String.class),				
	@ColumnResult(name = "couponSalerate", type = int.class),				
	@ColumnResult(name = "couponStartdate", type = String.class),				
	@ColumnResult(name = "couponStatus", type = String.class),				
	@ColumnResult(name = "foodCode", type = String.class),	
	@ColumnResult(name = "foodAddr", type = String.class),
	@ColumnResult(name = "foodArea", type = String.class),
	@ColumnResult(name = "foodDate", type = Date.class),
	@ColumnResult(name = "foodIntro", type = String.class),
	@ColumnResult(name = "foodKind", type = String.class),
	@ColumnResult(name = "foodMenu", type = String.class),
	@ColumnResult(name = "foodName", type = String.class),
	@ColumnResult(name = "foodPhone", type = String.class),				
	@ColumnResult(name = "foodRead", type = int .class),								
	@ColumnResult(name = "foodTime", type = String.class),				
	@ColumnResult(name = "memberCode", type = String.class),				
	@ColumnResult(name = "imageCode", type = String.class),				
	@ColumnResult(name = "imageName", type = String.class),				
	@ColumnResult(name = "imagePath", type = String.class),				
	@ColumnResult(name = "imageSize", type = long.class),				
	@ColumnResult(name = "referCode", type = String.class)				
	}				
	)				
	)		










@Table(name = "food")
@ToString
@Entity
@Getter
@Setter

public class Food {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="food_Code", nullable = false)
	private String foodCode;	// 음식점코드
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "member_id") private Member member;
	 */
	
	@Column(name = "food_name", nullable = false)
	private String foodName;	// 음식점 명
	
	@Column(name = "food_addr", nullable = false)
	private String foodAddr;	// 음식점 주소
	
	private String foodArea;	// 음식점 지역
	
	@Column(name = "food_phone", nullable = false)
	private String foodPhone;	// 음식점 전화번호
	
	@Column(name = "food_kind", nullable = false)
	private String foodKind;	// 음식 분류  ex) 한식, 중식, 
	
	@Column(name = "food_menu")
	private String foodMenu;	// 음식 대표메뉴
	
	@Column(name = "food_time")
	private String foodTime;	// 음식점 영업시간
	
	@Column(name = "food_break")
	private String foodBreak;	// 음식점 휴일
	
	@Lob
	@Column(name = "food_intro")
	private String foodIntro;	// 음식점 소개
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "food_date", nullable = false)
	private Date foodDate;	// 음식점 등록일
	
	@Column(name = "food_read", nullable = false)
	private int foodRead;	// 음식점 조회 카운트
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "food_status")
	private String foodStatus;	//	음식점 상태  ex) y : 완료, n : 검토중	
	// private FoodStatus foodStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_code")
	private Member member;	// 등록자 
	



}
