package com.travel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.booking.model.RouteBusSeat;

public interface RouteBusSeatRepository  extends JpaRepository<RouteBusSeat,Long> {
	

}
