package com.travel.bus.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VendorAccountDTO {

	private Long Id;
	
	private String accountType;
	
	private double minimumBalance;
	
	private double currentBalance;	
	
	private String bankName;
	
	private Long vendorId;
	
	@JsonIgnore
	private List<PaymentDTO> payments = new ArrayList<>();


	public VendorAccountDTO(String accountType, double minimumBalance, double currentBalance,String bankName,Long vendorId) {
		this.accountType = accountType;
		this.minimumBalance = minimumBalance;
		this.currentBalance = currentBalance;
		this.bankName =  bankName;
		this.vendorId =vendorId;
	}

}
