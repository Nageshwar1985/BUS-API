package com.travel.payment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.payment.dto.PaymentRequestDTO;
import com.travel.payment.model.CustomerAccount;
import com.travel.payment.model.Transfer;
import com.travel.payment.model.VendorAccount;
import com.travel.payment.service.PaymentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PaymentController {

	Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private PaymentService paymentService;

	@Autowired
	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@ApiOperation(value = "Gets payment Details for given payment id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Payment not found") })

	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<Transfer> getPayment(@PathVariable Long paymentId) {
		logger.info("get payment details for id : {} ", paymentId);
		return new ResponseEntity<>(paymentService.getPaymentTransfer(paymentId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all payments and their Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Payment not found") })

	@GetMapping("/payments")
	public ResponseEntity<List<Transfer>> getPayments() {
		logger.info("get payments details for id : {} ");
		return new ResponseEntity<>(paymentService.getPaymentTransfers(), HttpStatus.OK);
	}

	@ApiOperation(value = "pays the oreder amount for selected products to vendor from customer and returns reciept")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "order not found") })

	@PostMapping("/payments")
	public ResponseEntity<Transfer> placePayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {

		logger.info("add payment details : {} ", paymentRequestDTO);
		return new ResponseEntity<>(
				paymentService.placePayment(paymentRequestDTO.getBookingId(), paymentRequestDTO.getUserId()),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "adds the new customer bank account details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "account not found") })
	@PostMapping("/payment/createcustomeraccount")
	public ResponseEntity<CustomerAccount> createCustomerAccount(@Valid @RequestBody CustomerAccount customerAccount) {
		return new ResponseEntity<>(paymentService.createCustomerAccount(customerAccount), HttpStatus.CREATED);
	}

	@ApiOperation(value = "adds the new vendor bank account details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "account not found") })

	@PostMapping("/payment/createvendoraccount")
	public ResponseEntity<VendorAccount> createVendorAccount(@Valid @RequestBody VendorAccount vendorAccount) {
		return new ResponseEntity<>(paymentService.createVendorAccount(vendorAccount), HttpStatus.CREATED);
	}

}
