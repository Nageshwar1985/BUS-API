package com.travel.bus.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "isActive","loggedInAt" })
public class LoginDTO {

	
	private Long id;
	
	@NotNull	
	private String loginName;
	
	@NotNull
	private String password;

	@JsonIgnore
	private boolean isActive;


	private Timestamp loggedInAt;

	public LoginDTO(String password, boolean isActive) {
		this.password = password;
		this.isActive = isActive;
	}

}
