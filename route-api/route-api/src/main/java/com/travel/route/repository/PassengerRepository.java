package com.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.route.model.Passenger;

public interface PassengerRepository  extends JpaRepository<Passenger,Long> {
	

}
	