package com.travel.route.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.route.dto.CartDTO;
import com.travel.route.dto.RouteBusSeatDTO;
import com.travel.route.dto.SeatSelectionDTO;
import com.travel.route.exception.ResourceNotFoundException;
import com.travel.route.mapper.CartMapper;
import com.travel.route.mapper.RouteBusSeatMapper;
import com.travel.route.model.Bus;
import com.travel.route.model.Cart;
import com.travel.route.model.Passenger;
import com.travel.route.model.Route;
import com.travel.route.model.RouteBusSeat;
import com.travel.route.repository.BusRepository;
import com.travel.route.repository.CartRepository;
import com.travel.route.repository.PassengerRepository;
import com.travel.route.repository.RouteBusSeatRepository;
import com.travel.route.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

	private RouteRepository routeRepository;

	private BusRepository busRepository;
	
	private CartRepository cartRepository;
	
	private RouteBusSeatRepository routeBusSeatRepository;
	
	private PassengerRepository passengerRepository;

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private RouteBusSeatMapper routeBusSeatMapper;

	@Autowired
	RouteServiceImpl(RouteRepository routeRepository,BusRepository busRepository, CartRepository cartRepository,RouteBusSeatRepository routeBusSeatRepository,PassengerRepository passengerRepository) {
		this.routeRepository = routeRepository;
		this.busRepository = busRepository;
		this.cartRepository = cartRepository;
		this.routeBusSeatRepository = routeBusSeatRepository;
		this.passengerRepository =passengerRepository;
	}

	
	public CartDTO getCart(Long cartId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));
		CartDTO cartDTO =	cartMapper.toDto(cart);
		List<RouteBusSeatDTO> seatList= new ArrayList<>();
		for(RouteBusSeatDTO routeBusSeatDTO :cartDTO.getSeats()) {
			
			seatList.add(routeBusSeatMapper.toDto( cart.getRouteBusSeats().stream().filter(seat -> seat.getId().equals(routeBusSeatDTO.getId())).findFirst().orElse(null)));
			logger.info("seatList  : {}", seatList);
		}
		cartDTO.setSeats(seatList);
		
		logger.info("get  Cart details response ", cart);
		return cartDTO;
	}
	
	
	public List<Bus> addBuses(List<Bus> buses) {

		List<Bus> addedBusList = new ArrayList<>();

		addedBusList = buses.stream().map(b -> busRepository.save(b)).collect(Collectors.toList());
		logger.info("saved BusList details  ", addedBusList);

		return addedBusList;
	}
	
	public List<Route> addRoutes(List<Route> routes) {
		Map<String, List<Bus>> routeBusMap = new HashMap<>();
		List<Route> routeList = new ArrayList<>();
		List<RouteBusSeat> routeBusSeats = new ArrayList<>();

		for (Route route : routes) {
			List<Bus> existingBusList = new ArrayList<>();
			for (Bus bus : route.getBuses()) {
				existingBusList.add(busRepository.findById(bus.getId()).orElse(null));
			}
			routeBusMap.put(route.getRouteNo(), existingBusList);
		}
		
		
		logger.info("routeBusMap details  {}", routeBusMap);
		for (Route route : routes) {
			route.setBuses(routeBusMap.get(route.getRouteNo()));
			
			routeList.add(route);
		}
		
		
		List<Route> addedRouteList = routeRepository.saveAll(routeList);
				
		logger.info("addedRouteList details  {}", addedRouteList);
		
		for (Route route : addedRouteList) {
			for (Bus bus : route.getBuses()) {
				
				 bus.setRoute(route);
				 busRepository.save(bus);
				for(int seat =1; seat <= bus.getSeatCount().intValue(); seat++) {
				RouteBusSeat routeBusSeat  = new RouteBusSeat("Yes", Integer.valueOf(seat)); 
				logger.info("bus details {} ", bus);
				routeBusSeat.setBus(bus);
				logger.info("route details {} ", route);
				routeBusSeat.setRoute(route);
				logger.info("routeBusSeat details {} ", routeBusSeat);
				 routeBusSeats.add(routeBusSeat);
			}
				
				logger.info("routeBusSeats details {} ", routeBusSeats);
				routeBusSeatRepository.saveAll(routeBusSeats );
			
			}
		
			
			}
		
		return addedRouteList;
	}
	
	
	public List<Route> getRoutes() {

		List<Route> routes = routeRepository.findAll();
		logger.info("get all Route details response {} ", routes);
		return routes;
	}

	public Route getRoute(Long routeId) {
		Route route = routeRepository.findById(routeId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  Route details response {}", route);
		logger.info("get  Route Bus details response {}", route.getBuses());
		return route;
	}
	
	public List<Bus> getBuses() {

		List<Bus> buses = busRepository.findAll();
		logger.info("get all Route details response {}", buses);
		return buses;
	}

	public Bus getBus(Long busId) {
		Bus bus = busRepository.findById(busId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  bus details response {}", bus);
		return bus;
	}
	
	public List<RouteBusSeat> getRouteBusSeats() {

		List<RouteBusSeat> routeBusSeats = routeBusSeatRepository.findAll();
		logger.info("get all Route details response {} ", routeBusSeats);
		return routeBusSeats;
	}

	public RouteBusSeat getRouteBusSeat(Long routeBusSeatId) {
		RouteBusSeat routeBusSeat = routeBusSeatRepository.findById(routeBusSeatId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  RouteBusSeat details response  {}", routeBusSeat);
		return routeBusSeat;
	}
	
	public CartDTO selectSeats( SeatSelectionDTO seatSelectionDTO) {
		
		Cart existingCart = null;
		List<RouteBusSeat> updatedRouteBusSeats = new ArrayList<>();
		List<RouteBusSeat> finalizedRouteBusSeats = new ArrayList<>();
		List<RouteBusSeat> existingRouteBusSeats = seatSelectionDTO.getPassengers().stream()
				               .map(pass -> routeBusSeatRepository.findById(pass.getSeatId()).orElse(null)).collect(Collectors.toList());

		logger.info("existing products  response {}", existingRouteBusSeats);

		if (null != existingRouteBusSeats) {
			
			
			
			existingCart = existingRouteBusSeats.stream().filter(prod -> null != prod.getCart()).findFirst()
					.map(p -> p.getCart()).orElse(null);
			
			Double cartTotalAmount = existingRouteBusSeats.stream().filter(Objects::nonNull)
					                                                     .map(seat -> routeRepository.findById(seat.getRoute().getId()).orElse(null))
					                                                     .filter(prod -> null != prod.getFare())
					                                                     .map(p -> p.getFare())
					                                                     .collect(Collectors.summingDouble(Double::doubleValue));
			

			if (existingCart == null) {
				existingCart =cartRepository.save(new Cart("open", existingRouteBusSeats,cartTotalAmount));
			}
			Cart latestCart = existingCart;
			logger.info("saving product  : {}", latestCart);
			logger.info("existing products  response {}", existingRouteBusSeats);

			for (RouteBusSeat routeBusSeat : existingRouteBusSeats) {
				seatSelectionDTO.getPassengers().stream().filter(pass -> routeBusSeat.getId().equals(pass.getSeatId())).forEach(pass -> {
					Passenger passenger =passengerRepository.save(new Passenger(pass.getFirstName(), pass.getLastName(), pass.getAge(), pass.getGender()));
					routeBusSeat.setPassenger(passenger);
				});
				if(null == routeBusSeat.getCart())
				routeBusSeat.setCart(latestCart);
				logger.info("saving product  : {}", routeBusSeat);
				updatedRouteBusSeats.add(routeBusSeatRepository.save(routeBusSeat));

			}
//			updatedRouteBusSeats =routeBusSeatRepository.saveAll(finalizedRouteBusSeats);
		}

		Cart cart = updatedRouteBusSeats.stream().map(p -> p.getCart()).findFirst().orElse(null);
		logger.info("cart details  : {}", cart);
		CartDTO cartDTO = cartMapper.toDto(cart);
		List<RouteBusSeatDTO> seatList= new ArrayList<>();
		for(RouteBusSeatDTO routeBusSeatDTO :cartDTO.getSeats()) {
			
			seatList.add(routeBusSeatMapper.toDto( cart.getRouteBusSeats().stream().filter(seat -> seat.getId().equals(routeBusSeatDTO.getId())).findFirst().orElse(null)));
			logger.info("seatList  : {}", seatList);
		}
		cartDTO.setSeats(seatList);
		logger.info("cartDTO  : {}", cartDTO);
		return cartDTO;
	}
	

}
