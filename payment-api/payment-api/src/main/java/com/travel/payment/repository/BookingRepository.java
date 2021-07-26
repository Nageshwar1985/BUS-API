package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.Booking;

public interface BookingRepository  extends JpaRepository<Booking,Long> {
	

}
