package com.travel.bus.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrderProductDTO {
	
	private Long id;	

	
	private Long productId;
	
	private Long quantity;
	
	@JsonIgnore
	private OrderDTO order;
	
		
//	@JsonIgnore
//	@OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
//	private List<Product> products = new ArrayList<>();
	
	public OrderProductDTO(Long productId, Long quantity ) {
	this.productId=productId;
	this.quantity=quantity;
	
	}

}
