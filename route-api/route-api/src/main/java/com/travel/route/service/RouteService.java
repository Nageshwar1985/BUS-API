package com.travel.route.service;

import java.util.List;

import com.travel.route.dto.CartDTO;
import com.travel.route.dto.SeatSelectionDTO;
import com.travel.route.model.Bus;
import com.travel.route.model.Route;
import com.travel.route.model.RouteBusSeat;

public interface RouteService {


//	public List<Product> getProducts();
//
//	public Product getProduct(Long productId);
//
//	public Product addProduct(Product product);
//
//	public Product updateProduct(Long productId, Product product);
//
//	public void deleteProduct(Long productId);
//	
//	public CartDTO selectProducts(List<Long> productList) ;
//	
	public CartDTO getCart(Long cartId);
	
	
	public List<Bus> addBuses(List<Bus> buses) ;
	
	public List<Route> addRoutes(List<Route> routes) ;
	
	public List<Route> getRoutes() ;

	public Route getRoute(Long routeId) ;
	
	public List<Bus> getBuses();
	public Bus  getBus(Long busId) ;
	
	public List<RouteBusSeat> getRouteBusSeats() ;

	public RouteBusSeat getRouteBusSeat(Long routeBusSeatId);
	
	public CartDTO selectSeats( SeatSelectionDTO seatSelectionDTO) ;
	
}
