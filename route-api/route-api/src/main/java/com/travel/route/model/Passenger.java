package com.travel.route.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long id;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "first_name")
	private String firstName;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column(name = "age")
	private Long age;
	@NotNull
	@Column(name = "gender")
	private String gender;
	
//	@ToString.Exclude
	@JsonIgnore
	@OneToOne(mappedBy ="passenger",cascade = CascadeType.ALL)
	private RouteBusSeat routeBusSeat ;
	
	public Passenger(String firstName, String lastName, Long age, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

}
