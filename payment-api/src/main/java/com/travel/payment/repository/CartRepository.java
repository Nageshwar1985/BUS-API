package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
