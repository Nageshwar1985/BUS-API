package com.travel.bus.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@EqualsAndHashCode(exclude={"id","routeNo","routeName","fare","buses","routeBusSeats"})
public class RouteDTO {


	private Long id;


	private String routeNo;
	private String routeName;

	@NotBlank
	private String source;

	@NotBlank
	private String destination;


	private Double fare;
	
	@ToString.Exclude
	private List<BusDTO> buses;
	
	@ToString.Exclude
	private List<RouteBusSeatDTO> routeBusSeats;

}
