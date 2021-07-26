package com.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.route.model.Route;

public interface RouteRepository  extends JpaRepository<Route,Long> {
	

}
	