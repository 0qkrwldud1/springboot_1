package com.java.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "image")
@Getter 
@Setter
@ToString
public class Image {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "image_code" , nullable = false)
    private String imageCode;

	
    @Column(name="refer_code" , nullable = false)
	private String referCode;
    
    
    @Column(name="image_name" , nullable = false)
	private String imageName;
    
    
    @Column(name="image_size" , nullable = false)
	private long imageSize;
    
    
    @Column(name="image_path" , nullable = false)
	private String imagePath;


    }

