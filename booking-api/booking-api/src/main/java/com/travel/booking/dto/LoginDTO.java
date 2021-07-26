package com.travel.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "isActive", "role" })
public class LoginDTO {

	private Long id;

	private String password;

	private boolean isActive;

	private String role;

	public LoginDTO(String password, boolean isActive, String role) {
		this.password = password;
		this.isActive = isActive;
		this.role = role;
	}

}
