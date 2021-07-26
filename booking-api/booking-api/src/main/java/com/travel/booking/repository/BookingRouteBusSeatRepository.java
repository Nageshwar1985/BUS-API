package com.travel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.booking.model.BookingRouteBusSeat;

public interface BookingRouteBusSeatRepository  extends JpaRepository<BookingRouteBusSeat,Long> {
	

}
