package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.Product;

public interface ProductRepository  extends JpaRepository<Product,Long> {
	

}
