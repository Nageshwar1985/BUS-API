package com.travel.bus.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerAccountDTO {

	private Long Id;
	
	private String accountType;
	
	private double minimumBalance;
	
	private double currentBalance;	
	
	private String bankName;
	
	private Long customerId;
	
	@JsonIgnore
	private List<PaymentDTO> payments = new ArrayList<>();

	public CustomerAccountDTO(String accountType, double minimumBalance, double currentBalance,String bankName,Long customerId) {
		this.accountType = accountType;
		this.minimumBalance = minimumBalance;
		this.currentBalance = currentBalance;
		this.bankName =  bankName;
		this.customerId =customerId;
	}

}
