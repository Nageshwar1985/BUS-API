package com.travel.booking.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.booking.dto.CartDTO;


@FeignClient(name="route-api")
public interface RouteBusSeatClient {
	
	@GetMapping("/routes/cart/{cartId}")
	public CartDTO getCart(@PathVariable Long  cartId);
	
	@GetMapping("/carts")
	public List<CartDTO> getCarts();
	
	@PostMapping("/carts/")
	public CartDTO addCart(@Valid @RequestBody CartDTO cart);
	

}
