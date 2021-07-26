package com.travel.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.payment.model.CustomerAccount;
import com.travel.payment.model.Payment;
import com.travel.payment.model.Transfer;
import com.travel.payment.model.VendorAccount;

public interface VendorAccountRepository  extends JpaRepository<VendorAccount,Long> {
	

}
