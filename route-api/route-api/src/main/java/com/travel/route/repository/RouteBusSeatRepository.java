package com.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.route.model.RouteBusSeat;

public interface RouteBusSeatRepository  extends JpaRepository< RouteBusSeat,Long> {
	

}
