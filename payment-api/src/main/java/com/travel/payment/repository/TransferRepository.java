package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.Payment;
import com.travel.payment.model.Transfer;

public interface TransferRepository  extends JpaRepository<Transfer,Long> {
	

}
