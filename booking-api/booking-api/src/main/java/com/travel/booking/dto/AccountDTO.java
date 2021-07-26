package com.travel.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AccountDTO {

	private Long accoutNumber;
	
	@NotBlank
	private String accountType;
	@NotNull
	private double minimumBalance;
	
	@NotNull
	private double currentBalance;	
	
	private CustomerDTO customerDTO;

	public AccountDTO(String accountType, double minimumBalance, double currentBalance) {
		this.accountType = accountType;
		this.minimumBalance = minimumBalance;
		this.currentBalance = currentBalance;
	}

}
