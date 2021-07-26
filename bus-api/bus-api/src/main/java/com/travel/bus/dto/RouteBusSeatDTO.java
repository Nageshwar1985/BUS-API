package com.travel.bus.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RouteBusSeatDTO {

	private Long id;


	private String available;

	
	

	private Integer seatNumber;
	
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id", referencedColumnName = "id")
	@JsonIgnore
	private RouteDTO route;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private BusDTO bus;
	
//	@ToString.Exclude
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "cart_id", referencedColumnName = "id")
//	private Cart cart;
	
//	@ToString.Exclude
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "passenger_id", referencedColumnName = "id")
//	private Passenger passenger;
	
	public RouteBusSeatDTO(String available,Integer seatNumber){
		this.available=available;
		this.seatNumber=seatNumber;
	}
	

}
