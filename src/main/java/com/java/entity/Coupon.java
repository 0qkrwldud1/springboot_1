package com.java.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.Table;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.CouponDto;
import com.java.dto.SearchCouponDto;
import com.java.dto.SearchFoodDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="coupon")
 
@Getter
@Setter
@ToString
	// couponCount ,couponrepo
	@NamedNativeQuery(
			name = "countByKeywordLike",
			query = "SELECT count(*) FROM coupon as c, food as f"
				+ "WHERE c.food_code = f.coupon_code "
				+ "AND coupon_name like %:keyword%"
				+ "OR food_name like %:keyword%"
				+ "OR food_kind like %:keyword%"
				+ "OR food_menu like %:keyword%"
				+ "OR food_tag like %:keyword%",
				resultSetMapping = "countByKeywordLike"
			)
	@SqlResultSetMapping(
		    name = "countByKeywordLike",
		    classes = @ConstructorResult(
		        targetClass = SearchFoodDto.class,
		        columns = {
		            @ColumnResult(name = "coupon_name", type = String.class),
		            @ColumnResult(name = "food_name", type = String.class),
		            @ColumnResult(name = "food_kind", type = String.class),
		            @ColumnResult(name = "food_menu", type = String.class),
		            @ColumnResult(name = "food_tag", type = String.class)
		            
		        }
		    )
		)

	
	// couponList, couponrepo
	@NamedNativeQuery(
		name = "findByKeywordLike",
		query =  "SELECT * FROM coupon c, food f, image i "
			+ "WHERE c.coupon_code = f.coupon_code "
			+ "AND c.coupon_code = i.refer_code(+)"
			+ "AND coupon_name like %:keyword%"
			+ "OR food_name like %:keyword%"
			+ "OR food_kind like %:keyword%"
			+ "OR food_menu like %:keyword%"
			+ "OR food_tag like %:keyword%"
			+ "ORDERBY c.coupon_salerate DESC"
			)
	@SqlResultSetMapping(
		    name = "findByKeywordLike",
		    classes = @ConstructorResult(
		        targetClass = SearchFoodDto.class,
		        columns = {
		            @ColumnResult(name = "coupon_name", type = String.class),
		            @ColumnResult(name = "food_name", type = String.class),
		            @ColumnResult(name = "food_kind", type = String.class),
		            @ColumnResult(name = "food_menu", type = String.class),
		            @ColumnResult(name = "food_tag", type = String.class)
		            
		        }
		    )
		)



public class Coupon {

	// 기본키 pk = not null + unique
    @Id 
    @Column(name="coupon_code")
    private String couponCode;       //상품 코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_code")
    private Food food;    
    
    @Column(name = "coupon_name", nullable = false, length = 500)
    private String couponName;
    
    @Column(name = "coupon_startdate", nullable = false)
    private Date couponStartdate;
    
    @Column(name = "coupon_enddate", nullable = false)
    private Date couponEnddate;

    @Column(name = "coupon_costori", nullable = false, length = 20)
    private int couponCostori;	
    
    @Column(name = "coupon_costsale", nullable = false, length = 3)
    private int couponCostsale;
    
    @Column(name = "coupon_salerate", nullable = false, length = 20)
	private int couponSalerate;
    
    @Lob
    @Column(name = "coupon_intro", length = 4000)
    private String couponIntro;
    
    @Column(name = "coupon_status", length = 20)
	private String couponStatus;
	 

   
    
    
}





