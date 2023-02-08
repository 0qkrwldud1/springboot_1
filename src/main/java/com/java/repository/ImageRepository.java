package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
