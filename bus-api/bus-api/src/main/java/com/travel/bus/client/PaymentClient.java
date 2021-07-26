package com.travel.bus.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.bus.dto.PaymentRequestDTO;
import com.travel.bus.dto.TransferDTO;

@FeignClient(name="payment-api")
public interface PaymentClient {
	
	@PostMapping("/payments")
	public TransferDTO placePayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) ;
}
