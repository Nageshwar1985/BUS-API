package com.travel.route.controller;

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

import com.travel.route.dto.CartDTO;
import com.travel.route.dto.SeatSelectionDTO;
import com.travel.route.model.Bus;
import com.travel.route.model.Route;
import com.travel.route.service.RouteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RouteController {

	Logger logger = LoggerFactory.getLogger(RouteController.class);

	private RouteService routeService;

	@Autowired
	RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@ApiOperation(value = "Gets Route Details for a given route Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Route not found") })
	@GetMapping("/routes/{routeId}")
	public ResponseEntity<Route> getRoute(@PathVariable Long routeId) {
		logger.info("get route details for id : {} ", routeId);
		return new ResponseEntity<>(routeService.getRoute(routeId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all routes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Routes not found") })
	@GetMapping("/routes")
	public ResponseEntity<List<Route>> getRoutes() {
		logger.info("get routes details for id : {} ");
		return new ResponseEntity<>(routeService.getRoutes(), HttpStatus.OK);
	}

	@ApiOperation(value = "create and add new routes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Routes not found") })
	@PostMapping("/routes")
	public ResponseEntity<List<Route>> addRoute(@Valid @RequestBody List<Route> routes) {

		logger.info("add route details : {} ", routes);
		return new ResponseEntity<>(routeService.addRoutes(routes), HttpStatus.CREATED);
	}
	
	
	
	@ApiOperation(value = "Gets Bus Details for a given route Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Route not found") })
	@GetMapping("/buses/{busId}")
	public ResponseEntity<Bus> getBus(@PathVariable Long busId) {
		logger.info("get bus details for id : {} ", busId);
		return new ResponseEntity<>(routeService.getBus(busId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all buses")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Routes not found") })
	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> getBuses() {
		logger.info("get bus details for id : {} ");
		return new ResponseEntity<>(routeService.getBuses(), HttpStatus.OK);
	}

	@ApiOperation(value = "create and add new routes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Routes not found") })
	@PostMapping("/buses")
	public ResponseEntity<List<Bus>> addBuses(@Valid @RequestBody List<Bus> buses) {

		logger.info("add route details : {} ", buses);
		return new ResponseEntity<>(routeService.addBuses(buses), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Adds the selected route to cart and gives cart id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Route not found") })
	@PostMapping("/routes/select")
	public ResponseEntity<CartDTO> selectSeat(@RequestBody SeatSelectionDTO seatSelectionDTO) {
		logger.info("select route details : {} ", seatSelectionDTO);
		return new ResponseEntity<>(routeService.selectSeats(seatSelectionDTO), HttpStatus.OK);
	}
	

//	@ApiOperation(value = "update existing route with latest details")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
//			@ApiResponse(code = 500, message = "Internal server error"),
//			@ApiResponse(code = 404, message = "Routes not found") })
//	@PutMapping("/routes/{id}")
//	public ResponseEntity<Route> updateRoute(@PathVariable("id") Long id, @Valid @RequestBody Route route) {
//		logger.info("update route details : {} ", route);
//		return new ResponseEntity<>(routeService.updateRoute(id, route), HttpStatus.OK);
//	}

//	@ApiOperation(value = "Deletes existing route ")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
//			@ApiResponse(code = 500, message = "Internal server error"),
//			@ApiResponse(code = 404, message = "Routes not found") })
//	@DeleteMapping("/routes/{id}")
//	public ResponseEntity<String> deleteRoute(@PathVariable("id") Long id) {
//		logger.info("delete route details for id : {} ", id);
//		routeService.deleteRoute(id);
//		return new ResponseEntity<>("Requested Route has been deleted", HttpStatus.OK);
//	}

//	@ApiOperation(value = "Adds the selected route to cart and gives cart id ")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
//			@ApiResponse(code = 500, message = "Internal server error"),
//			@ApiResponse(code = 404, message = "Route not found") })
//	@PostMapping("/routes/select")
//	public ResponseEntity<CartDTO> selectRoute(@RequestBody List<Long> ids) {
//		logger.info("select route details : {} ", ids);
//		return new ResponseEntity<>(routeService.selectRoutes(ids), HttpStatus.OK);
//	}
//
	@ApiOperation(value = "Gets all the routes for a given  cart id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "cart not found") })
	@GetMapping("/routes/cart/{cartId}")
	public ResponseEntity<CartDTO> getCart(@PathVariable("cartId") Long cartId) {
		logger.info("get cart details for id : {} ");
		return new ResponseEntity<>(routeService.getCart(cartId), HttpStatus.OK);
	}

}
