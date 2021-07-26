package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment,Long> {
	

}
