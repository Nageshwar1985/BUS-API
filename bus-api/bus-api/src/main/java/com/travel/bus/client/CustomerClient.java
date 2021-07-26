package com.travel.bus.client;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.bus.dto.CustomerDTO;
import com.travel.bus.dto.LoginDTO;

@FeignClient(name="customer-api")
public interface CustomerClient {
	Logger logger = LoggerFactory.getLogger(CustomerClient.class);
	
	@GetMapping("/customers/{customerId}")
	public CustomerDTO getCustomer(@PathVariable String  customerId);
	
	@GetMapping("/customers")
	public List<CustomerDTO> getCustomers();
	
	@PostMapping("/customers/")
	public LoginDTO addCustomer(@Valid @RequestBody CustomerDTO customer);
	
	@PostMapping("/customers/login")
	public LoginDTO loginCustomer(@Valid @RequestBody LoginDTO loginDTO);
	
//	@GetMapping("/customers/login/{id}")
//	public LoginDTO getLogin(@PathVariable Long id) ;
	
	@GetMapping("/customers/login/{loginName}")
	public LoginDTO getLogin(@PathVariable String loginName);
	
}

   
     

