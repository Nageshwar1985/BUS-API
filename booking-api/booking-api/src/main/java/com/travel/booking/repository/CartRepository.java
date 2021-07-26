package com.travel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.booking.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
