package com.travel.bus.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatSelectionDTO {

	private String loginName;
	private Long routeId;
	private Long busId;
	private Long cartId;
	private List<PassengerDTO> passengers = new ArrayList<>();

}
