package com.travel.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.customer.model.Customer;
import com.travel.customer.model.Login;
import com.travel.customer.service.CustomerRegistrationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private CustomerRegistrationService customerRegistrationService;

	@Autowired
	CustomerController(CustomerRegistrationService customerRegistrationService) {
		this.customerRegistrationService = customerRegistrationService;
	}

	@ApiOperation(value = "Gets all Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		logger.info("get all customer details  ");
		return new ResponseEntity<>(customerRegistrationService.getCustomers(), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets Customer Details for given customer id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
		logger.info("get customer details for id : {} ", id);
		return new ResponseEntity<>(customerRegistrationService.getCustomer(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Adds new Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@PostMapping("/customers/")
	public ResponseEntity<Login> addCustomer(@Valid @RequestBody Customer customer) {

		logger.info("add customer details : {} ", customer);
		return new ResponseEntity<>(customerRegistrationService.addCustomer(customer), HttpStatus.CREATED);
	}

	@ApiOperation(value = "update  Customer with latest Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id,
			@Valid @RequestBody Customer customer) {
		logger.info("update customer details : {} ", customer);
		return new ResponseEntity<>(customerRegistrationService.updateCustomer(id, customer), HttpStatus.OK);
	}

	@ApiOperation(value = "deletes existing Customer Details for given customerid")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
		logger.info("delete customer details for id : {} ", id);
		customerRegistrationService.deleteCustomer(id);
		return new ResponseEntity<>("Requested Customer has been deleted", HttpStatus.OK);
	}

	@ApiOperation(value = "gets login Details for given loginid")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })


	
	@GetMapping("/customers/login/{loginName}")
	public ResponseEntity<?> loggedIn(@PathVariable String loginName) {
		logger.info("get customer details for loginName : {} ", loginName);
		return new ResponseEntity<>(customerRegistrationService.getLogin(loginName), HttpStatus.OK);
	}
	
	@PostMapping("/customers/login")
	public ResponseEntity<Login> doLogin(@Valid @RequestBody Login login) {
		logger.info("do  login details for id : {} ", login);
		return new ResponseEntity<>(customerRegistrationService.doLogin(login), HttpStatus.OK);
	}

}
