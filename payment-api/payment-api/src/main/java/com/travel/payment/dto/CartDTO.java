package com.travel.payment.dto;

import java.util.ArrayList;
import java.util.List;

import com.travel.payment.model.Product;

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
