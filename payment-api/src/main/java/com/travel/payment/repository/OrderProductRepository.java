package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.BookingRouteBusSeat;

public interface OrderProductRepository  extends JpaRepository<BookingRouteBusSeat,Long> {
	

}
