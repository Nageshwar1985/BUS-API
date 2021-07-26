package com.travel.payment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	
	@Column(name = "productId")
	private Long productId;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	@JsonIgnore
	private Booking booking;
	
		
//	@JsonIgnore
//	@OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
//	private List<Product> products = new ArrayList<>();
	
	public BookingRouteBusSeat(Long productId, Long quantity ) {
	this.productId=productId;
	this.quantity=quantity;
	
	}

}
