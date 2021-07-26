package com.travel.booking.model;

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
@Entity
@Table(name = "routebusseat")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RouteBusSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "available")
	private String available;

	
	

	@Column(name = "seatNumber")
	private Integer seatNumber;
	
	
//	@ToString.Exclude
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "route_id", referencedColumnName = "id")
//	@JsonIgnore
//	private Route route;
//	
//	@ToString.Exclude
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "bus_id", referencedColumnName = "id")
//	@JsonIgnore
//	private Bus bus;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passenger_id", referencedColumnName = "id")
	private Passenger passenger;
	
	public RouteBusSeat(String available,Integer seatNumber){
		this.available=available;
		this.seatNumber=seatNumber;
	}
	

}
