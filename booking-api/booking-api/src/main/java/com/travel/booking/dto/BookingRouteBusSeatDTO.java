package com.travel.booking.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRouteBusSeatDTO {

	private Long id;
	@Column(name = "name")
	private String name;
	private String desc;

	private Integer quantity;


	private String category;
	private Long vendorNo;
	
	private Double price;

	private String status;
	@JsonIgnore
	private CartDTO cartDTO;

}
