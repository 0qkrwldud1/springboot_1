package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.SearchCouponDto;
import com.java.entity.Coupon;
import com.java.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
		
}
