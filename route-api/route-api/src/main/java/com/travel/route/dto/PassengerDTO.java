package com.travel.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

	private String firstName;
	private String lastName;
	private Long age;
	private String gender;
	private Integer seatNumber;
	private Long seatId;
	
	
}
