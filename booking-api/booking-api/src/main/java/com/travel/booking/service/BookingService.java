package com.travel.booking.service;

import java.util.List;

import com.travel.booking.dto.BookingDTO;
import com.travel.booking.dto.CartDTO;
import com.travel.booking.model.Booking;
import com.travel.booking.model.RouteBusSeat;

public interface BookingService {


//	public List<RouteBusSeat> getRouteBusSeats();

//	public RouteBusSeat getRouteBusSeat(Long productId);

//	public RouteBusSeat addRouteBusSeat(RouteBusSeat product);

	public RouteBusSeat updateRouteBusSeat(Long productId, RouteBusSeat product);

	public void deleteRouteBusSeat(Long productId);
	
	public CartDTO selectRouteBusSeats(List<Long> productList) ;
	
//	public CartDTO getCart(Long cartId);
	
	public BookingDTO  placeBooking( Long cartId,Long userId) ;
	
	public List<BookingDTO> getBookings() ;

	public BookingDTO getBooking(Long orderId);
	
	public BookingDTO  updateBooking( Long bookingId, BookingDTO booking);
}
