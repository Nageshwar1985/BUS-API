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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BusDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	private String busNo;
		
	@NotBlank
	private String busType;
	

	@NotNull
	private Integer seatCount;
	
	
	@ToString.Exclude
	@JsonIgnore
	private RouteDTO route;
	
	
	@ToString.Exclude
	private List<RouteBusSeatDTO> routeBusSeats;
	

}