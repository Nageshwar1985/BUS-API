package com.travel.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RouteBusSeatDTO {


	private Long id;
	private String available;	
	private Integer seatNumber;
	private PassengerDTO passenger;
	
	public RouteBusSeatDTO(String available,Integer seatNumber){
		this.available=available;
		this.seatNumber=seatNumber;
	}
	

}
