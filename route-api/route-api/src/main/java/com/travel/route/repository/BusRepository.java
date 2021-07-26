package com.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.route.model.Bus;

public interface BusRepository  extends JpaRepository<Bus,Long> {
	

}
