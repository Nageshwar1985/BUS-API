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
public class PurchaseResponseDTO {

	private Long transactionId;

	private double paidAmount;
	
	private String status;
	
	private String message;

	private List<RouteBusSeatDTO> seats = new ArrayList<>();

	public PurchaseResponseDTO(Long transactionId,double paidAmount, String status,String message) {
		this.transactionId= transactionId;
		this.paidAmount = paidAmount;
		this.status = status;
		this.message =  message;
	}

}
