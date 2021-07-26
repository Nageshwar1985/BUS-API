package com.travel.bus.controller;

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

import com.travel.bus.dto.BusDTO;
import com.travel.bus.dto.CustomerDTO;
import com.travel.bus.dto.LoginDTO;
import com.travel.bus.dto.LoginResponseDTO;
import com.travel.bus.dto.OrderDTO;
import com.travel.bus.dto.PurchaseResponseDTO;
import com.travel.bus.dto.RouteDTO;
import com.travel.bus.dto.SeatSelectionDTO;
import com.travel.bus.service.BusService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BusController {

	Logger logger = LoggerFactory.getLogger(BusController.class);

	private BusService busService;

	@Autowired
	BusController(BusService busService) {
		this.busService = busService;
	}

	@ApiOperation(value = "Gets Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@GetMapping("/bus/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
		logger.info("get customer details for id : {} ", customerId);
		return new ResponseEntity<>(busService.getCustomer(customerId), HttpStatus.OK);
	}

	@ApiOperation(value = "Register new Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@PostMapping("/bus/customers")
	public ResponseEntity<LoginResponseDTO> registerCustomer(@Valid @RequestBody CustomerDTO customer) {

		logger.info("add customer details : {} ", customer);
		return new ResponseEntity<>(busService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Login  and access Customer operations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@PostMapping("/bus/customers/login")
	public ResponseEntity<?> loginCustomer(@Valid @RequestBody LoginDTO loginDTO) {

		logger.info("login customer details : {} ", loginDTO);
		return new ResponseEntity<>(busService.loginCustomer(loginDTO), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Gets all available Products ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@GetMapping("/bus/routes")
	public ResponseEntity<List<RouteDTO>> getRoutes() {
		return new ResponseEntity<>(busService.getRoutes(), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets  Product for a given Id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Product not found") })
	@PostMapping("/bus/routes/buses")
	public ResponseEntity<List<BusDTO>> getBuses(@Valid @RequestBody RouteDTO routeDTO) {
		logger.info("get product details for id : {} ", routeDTO);
		return new ResponseEntity<>(busService.getBuses(routeDTO), HttpStatus.OK);
	}

	@ApiOperation(value = " validates Customer login and buy the selected seats and gets conformation including payment  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "seats not found") })
	@PostMapping("/bus/seats")
	public ResponseEntity<PurchaseResponseDTO> buyProduct(@Valid @RequestBody SeatSelectionDTO seatSelectionDTO) {
		logger.info("get seatSelectionDTO details for id : {} ", seatSelectionDTO);
		return new ResponseEntity<>(busService.bookSeat(seatSelectionDTO), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all available orders for a selected customer  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Order not found") })

	@GetMapping("/bus/{customerId}/orders")
	public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long customerId) {
		logger.info("get customer details for id : {} ", customerId);
		return new ResponseEntity<>(busService.getOrders(customerId), HttpStatus.OK);
	}

	// Get orders

}
