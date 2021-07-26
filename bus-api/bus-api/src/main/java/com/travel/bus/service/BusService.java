package com.travel.bus.service;

import java.util.List;

import com.travel.bus.dto.BusDTO;
import com.travel.bus.dto.CustomerDTO;
import com.travel.bus.dto.LoginDTO;
import com.travel.bus.dto.LoginResponseDTO;
import com.travel.bus.dto.OrderDTO;
import com.travel.bus.dto.PurchaseResponseDTO;
import com.travel.bus.dto.RouteDTO;
import com.travel.bus.dto.SeatSelectionDTO;

public interface BusService {

	public List<CustomerDTO> getCustomers();

	public CustomerDTO getCustomer(String customerId);

	public LoginResponseDTO addCustomer(CustomerDTO customer);
	
//	public List<ProductDTO> getProducts() ;
	
//	public ProductDTO getProduct( Long productId);
	
//	public PurchaseResponseDTO buyProduct(LoginDTO loginDTO,List<Long> ids) ;
	
	public List<OrderDTO> getOrders(Long customerId) ;
	
	public String loginCustomer(LoginDTO loginDTO);
	
	public List<RouteDTO> getRoutes();
	
	public List<BusDTO> getBuses(RouteDTO routeDTO);
	
	public PurchaseResponseDTO bookSeat(SeatSelectionDTO seatSelectionDTO);
}
