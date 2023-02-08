package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.SearchCouponDto;
import com.java.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	// couponCount ,couponrepo
	@Query(nativeQuery = true ,
			name = "countByKeywordLike")
	int countByKeywordLike(@Param("keyword") String keyword);
	
	
	// couponList, couponrepo
	@Query(nativeQuery = true ,
			name = "findByKeywordLike")
	List<SearchCouponDto> findByKeywordLike(@Param("keyword") String keyword);
	
	@Query(nativeQuery = true ,
			value = "select * from (" +
                    "select a.id, a.name from test_table_a a left join test_table_b b on b.id = a.id where 조건절 union " +
                    "select c.id, c.name from test_table_c c left join test_table_d d on d.id = c.id where 조건절) e",
            countQuery = "select * from (" +
                    "select a.id, a.name from test_table_a a left join test_table_b b on b.id = a.id where 조건절 union " +
                    "select c.id, c.name from test_table_c c left join test_table_d d on d.id = c.id where 조건절) e")
   // PageImpl<Entity명> findBySomething(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

}
