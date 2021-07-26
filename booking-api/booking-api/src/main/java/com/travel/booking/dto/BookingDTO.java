package com.travel.booking.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.booking.model.RouteBusSeat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	
	private Long id;	

	private String status;
	
	
//	@JsonIgnore
//	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
//	private List<Product> products = new ArrayList<>();
//	
//	@JsonIgnore
//	@ManyToOne(mappedBy ="order",cascade = CascadeType.ALL)
//	private User user ;
	
	@JsonIgnore
	private List<RouteBusSeat> routeBusSeats = new ArrayList<>();
	
	private List<RouteBusSeatDTO> seats = new ArrayList<>();
	
	private Long cartId;    
	
	private Long userId;  
	
	private Double totalAmount;
	
	private Timestamp createdOn;
	
//	public BookingDTO(String status,Long cartId,List<UserBookingProduct> userBookingProducts) {
//	this.status=status;
//	this.cartId =cartId;
//	this.userBookingProducts=userBookingProducts;
//	}

}
