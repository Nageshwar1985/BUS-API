package com.travel.bus.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.bus.dto.BusDTO;
import com.travel.bus.dto.CartDTO;
import com.travel.bus.dto.ProductDTO;
import com.travel.bus.dto.RouteDTO;
import com.travel.bus.dto.SeatSelectionDTO;

@FeignClient(name="route-api")
public interface RouteClient {
	
	@GetMapping("/products/{productId}")
	public ProductDTO getProduct(@PathVariable Long  productId);
	
	@GetMapping("/products")
	public List<ProductDTO> getProducts();
		
	
	@PostMapping("/products/select")
	public CartDTO selectProduct(@Valid @RequestBody List<Long> ids);
	
	
	@GetMapping("/routes")
	public List<RouteDTO> getRoutes();
	
	@GetMapping("/routes/{routeId}")
	public List<RouteDTO> getRoute(@PathVariable Long  routeId);
	
	@GetMapping("/buses")
	public List<BusDTO> getBuses();
	
	@GetMapping("/buses/{busId}")
	public List<BusDTO> getBus(@PathVariable Long  busId);
	
	@PostMapping("/routes/select")
	public CartDTO selectSeat(@Valid @RequestBody SeatSelectionDTO seatSelectionDTO) ;

}
