package com.travel.booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.booking.dto.BookingDTO;
import com.travel.booking.dto.BookingRequestDTO;
import com.travel.booking.model.Booking;
import com.travel.booking.service.BookingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	private BookingService bookingService;

	@Autowired
	BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@ApiOperation(value = "Gets Booking Details for a given booking id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Booking not found") })
	@GetMapping("/bookings/{bookingId}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable Long bookingId) {
		logger.info("get booking details for id : {} ", bookingId);
		return new ResponseEntity<>(bookingService.getBooking(bookingId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all Bookings ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Bookings not found") })

	@GetMapping("/bookings")
	public ResponseEntity<List<BookingDTO>> getBookings() {
		logger.info("get bookings details for id : {} ");
		return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
	}


	@ApiOperation(value = "creates new booking for selected prodocts in cart ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Booking/products not found") })
	@PostMapping("/bookings")
	public ResponseEntity<BookingDTO> placeBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {

		logger.info("add booking details : {} ", bookingRequestDTO);
		return new ResponseEntity<>(bookingService.placeBooking(bookingRequestDTO.getCartId(), bookingRequestDTO.getUserId()),
				HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "update booking with latest details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Booking/products not found") })
	@PutMapping("/bookings/{bookingId}")
	public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingDTO bookingDTO) {

		logger.info("update booking details : {} ", bookingDTO);
		return new ResponseEntity<>(bookingService.updateBooking(bookingId, bookingDTO),
				HttpStatus.CREATED);
	}

}
