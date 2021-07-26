package com.travel.bus.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

	private Long cartId;	

	private String status;
	
	private Double totalAmount;
	
	private List<ProductDTO> products = new ArrayList<>();
	
	public CartDTO(String status, List<ProductDTO> products) {
		this.status=status;
		this.products=products;
		}

}
