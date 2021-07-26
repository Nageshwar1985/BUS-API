package com.travel.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {

	private Long id;
	private String name;
	private String desc;
	private Integer quantity;


	private String category;
	private Long vendorNo;

	private String status;
	
	private Double price;
	
	private CartDTO cart;
	
	private VendorDTO vendor;

}
