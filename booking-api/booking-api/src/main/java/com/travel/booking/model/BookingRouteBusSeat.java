package com.travel.booking.model;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingroutebusseat")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BookingRouteBusSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	
	@Column(name = "routebusseatId")
	private Long routebusseatId;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	@JsonIgnore
	private Booking booking;
	
		
//	@JsonIgnore
//	@OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
//	private List<RouteBusSeat> products = new ArrayList<>();
	
	public BookingRouteBusSeat(Long routebusseatId, Long quantity ) {
	this.routebusseatId=routebusseatId;
	this.quantity=quantity;
	
	}

}
