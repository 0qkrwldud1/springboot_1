package com.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="admin")
@Getter @Setter
@ToString
public class Admin {

    @Id
    @Column(name="admin_id")
	private String adminId;
    @Column(name="admin_pwd")
	private String adminPwd;
    

}
