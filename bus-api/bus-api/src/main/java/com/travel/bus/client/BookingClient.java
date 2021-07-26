package com.travel.bus.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.bus.dto.BookingDTO;
import com.travel.bus.dto.BookingRequestDTO;
import com.travel.bus.dto.OrderDTO;

@FeignClient(name="booking-api")
public interface BookingClient {
	
	@GetMapping("/orders/{orderId}")
	public OrderDTO getOrder(@PathVariable Long orderId);
	
	@GetMapping("/bookings")
	public List<OrderDTO> getOrders();
	
	@PostMapping("/bookings")
	public BookingDTO placeBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO);
	
	@PutMapping("/bookings/{bookingId}")
	public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingDTO bookingDTO);

}
