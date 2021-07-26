package com.travel.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.travel.payment.dto.BookingDTO;
import com.travel.payment.dto.OrderDTO;


@FeignClient(name="booking-api")
public interface BookingClient {
	
	@GetMapping("/bookings/{bookingId}")
	public BookingDTO getBooking(@PathVariable Long  bookingId);

}
