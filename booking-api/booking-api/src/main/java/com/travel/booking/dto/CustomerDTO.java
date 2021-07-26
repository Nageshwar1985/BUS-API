package com.travel.booking.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerDTO {

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long id;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	private String firstName;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	private String lastName;
	@NotNull
	private Integer age;
	@NotNull
	private String gender;
	@NotNull
	private String address;
	@NotNull
	private String city;
	@NotNull
	private String country;
	private Long income;
	@Email
	@NotNull
	private String email;
	private String status;
	@JsonIgnore
	private LoginDTO loginDTO;

	@JsonIgnore
	private List<AccountDTO> account;

}
