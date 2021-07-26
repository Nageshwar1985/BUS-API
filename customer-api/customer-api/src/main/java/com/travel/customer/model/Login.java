package com.travel.customer.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"id","isActive","loggedInAt"})
@Entity
@Table(name = "login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "loginname")
	private String loginName;
	
	@Column(name = "password")
	private String password;
	
	
	@JsonIgnore
	private String isActive;
	
	private Timestamp loggedInAt;
	
	public Login(String password,String isActive){
		this.password=password;		
		this.isActive=isActive;
	}
	

}
