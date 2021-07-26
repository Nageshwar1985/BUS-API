package com.travel.bus.dto;

import java.sql.Timestamp;
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
public class PaymentDTO {

	private Long id;	

	private String status;
	
	private Double totalAmount;
	
	
	private Timestamp updatedOn;
	
	private Timestamp createdOn;
	
	
	private CustomerAccountDTO customerAccountDTO;
	

	private VendorAccountDTO vendorAccountDTO;
	
	@JsonIgnore
	private List<TransferDTO> transferDTOs = new ArrayList<>();
	
//	
	
	private List<OrderProductDTO> orderProductDTOs = new ArrayList<>();
	
	private Long cartId;                      
	
//	public PaymentDTO(Long cartId,String status,Timestamp createdOn,Timestamp updatedOn,Double totalAmount) {
//	this.status=status;
//	this.cartId =cartId;
//	this.createdOn =createdOn;
//	this.updatedOn = updatedOn;
//	this.totalAmount=totalAmount;
//	}

}
