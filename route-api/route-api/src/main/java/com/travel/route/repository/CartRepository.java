package com.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.route.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
