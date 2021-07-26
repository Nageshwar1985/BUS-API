package com.travel.booking.dto;

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
	
private List<RouteBusSeatDTO> seats = new ArrayList<>();
	
	public CartDTO(String status, List<RouteBusSeatDTO> seats) {
		this.status=status;
		this.seats=seats;
		}


}
