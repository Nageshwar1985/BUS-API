package com.travel.bus.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.travel.bus.client.BookingClient;
import com.travel.bus.client.CustomerClient;
import com.travel.bus.client.PaymentClient;
import com.travel.bus.client.RouteClient;
import com.travel.bus.dto.BookingDTO;
import com.travel.bus.dto.BookingRequestDTO;
import com.travel.bus.dto.BusDTO;
import com.travel.bus.dto.CartDTO;
import com.travel.bus.dto.CustomerDTO;
import com.travel.bus.dto.LoginDTO;
import com.travel.bus.dto.LoginResponseDTO;
import com.travel.bus.dto.OrderDTO;
import com.travel.bus.dto.PaymentRequestDTO;
import com.travel.bus.dto.ProductDTO;
import com.travel.bus.dto.PurchaseResponseDTO;
import com.travel.bus.dto.RouteDTO;
import com.travel.bus.dto.SeatSelectionDTO;
import com.travel.bus.dto.TransferDTO;
import com.travel.bus.exception.ResourceNotFoundException;

@Service
public class BusServiceImpl implements BusService {

	Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

	private CustomerClient customerClient;

	private RouteClient routeClient;

	private BookingClient bookingClient;

	private PaymentClient paymentClient;
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	BusServiceImpl(CustomerClient customerClient, RouteClient routeClient, BookingClient bookingClient,
			PaymentClient paymentClient) {
		this.customerClient = customerClient;
		this.routeClient = routeClient;
		this.bookingClient = bookingClient;
		this.paymentClient = paymentClient;
	}

	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> customers = customerClient.getCustomers();
		logger.info("get all customer details response ", customers);
		return customers;
	}

	public CustomerDTO getCustomer(String customerId) {
		CustomerDTO customer = customerClient.getCustomer(customerId);
		logger.info("get  customer details response ", customer);
		return customer;
	}

	public  LoginResponseDTO  addCustomer(CustomerDTO customer) {
		logger.info("saving customer details  {}", customer);
		LoginResponseDTO  loginResponseDTO = null;
		CircuitBreaker customerCircuitbreaker = circuitBreakerFactory.create("customerCircuitbreaker");
		LoginDTO  loginDTO = customerCircuitbreaker.run(() -> customerClient.addCustomer(customer), throwable -> addDefaultCustomer(customer,throwable));
		if(loginDTO.getLoginName() != null)
			loginResponseDTO = new LoginResponseDTO("Login created", loginDTO);
		else
			loginResponseDTO = new LoginResponseDTO("Issue with internal service,Login is not created. Please try latter", loginDTO);
		return loginResponseDTO;
//		return customerClient.addCustomer(customer);
	}

	public String loginCustomer(LoginDTO loginDTO) {
		logger.info("Login customer details {}  ", loginDTO);
		LoginDTO login = customerClient.loginCustomer(loginDTO);
		return login != null ? "Customer successfully logged in" : "Sorry couldn't login , please try again";
	}

	public List<RouteDTO> getRoutes() {
		List<RouteDTO> routes = new ArrayList<>();
		routes = routeClient.getRoutes();
		return routes;
	}

	public List<BusDTO> getBuses(RouteDTO routeDTO) {
		List<RouteDTO> routes = new ArrayList<>();
		List<BusDTO> buses = new ArrayList<>();
		routes = routeClient.getRoutes();
		buses = routes.stream().filter(route -> route.equals(routeDTO)).flatMap(route -> route.getBuses().stream())
				.collect(Collectors.toList());
		return buses;
	}

	public ProductDTO getProduct(Long productId) {

		ProductDTO product = null;
		product = routeClient.getProduct(productId);
		return product;

	}

	public PurchaseResponseDTO bookSeat(SeatSelectionDTO seatSelectionDTO) {
		CartDTO cartDTO = null;
		BookingDTO bookingDTO = null;
		TransferDTO transferDTO = null;
		PurchaseResponseDTO purchaseResponseDTO = null;
		String message = "";
		LoginDTO loginDTO = getLoggedInDetails(seatSelectionDTO.getLoginName());
		if (null != loginDTO) {
			
			cartDTO = routeClient.selectSeat(seatSelectionDTO);

			logger.info("get  cartDTO details response {}", cartDTO);
			if (cartDTO != null)
				bookingDTO = bookingClient.placeBooking(new BookingRequestDTO(cartDTO.getCartId(), loginDTO.getId()));
			logger.info("Booking  Details {} ", bookingDTO);
			if (bookingDTO != null)
				transferDTO = paymentClient.placePayment(new PaymentRequestDTO(bookingDTO.getId(), loginDTO.getId()));

			logger.info("transfer Details {} ", transferDTO);
			if ("Success".equalsIgnoreCase(transferDTO.getStatus())) {
				bookingClient.updateBooking(bookingDTO.getId(), bookingDTO);
				message = "Selected seats reservation has been  completed successfully";
			} else {

				message = "Selected seats reservation has been failed, please try again";
			}

			purchaseResponseDTO = new PurchaseResponseDTO(transferDTO.getId(), transferDTO.getAmount(),
					transferDTO.getStatus(), message);
			purchaseResponseDTO.setSeats(bookingDTO.getSeats());

		} else {
			message = "Login expired, Please login again with credentails";
			purchaseResponseDTO = new PurchaseResponseDTO(Long.valueOf(0), Double.valueOf(0), "", message);
		}

		logger.info("purchaseResponseDTO Details {} ", purchaseResponseDTO);
		return purchaseResponseDTO;
	}

	public List<OrderDTO> getOrders(Long customerId) {
		List<OrderDTO> bookings = bookingClient.getOrders();
		logger.info("  booking details  ", bookings);
		List<OrderDTO> customerOrders = null;
		if (!CollectionUtils.isEmpty(bookings))
			customerOrders = bookings.stream().filter(o -> o.getUserId().equals(customerId))
					.collect(Collectors.toList());

		logger.info("get  booking details response ", customerOrders);
		return customerOrders;
	}

	public LoginDTO getLoggedInDetails(String loginName) {
		int loginValidTimeInMilliSeconds = 300000;
		Duration timeElapsed = null;
		LoginDTO login = customerClient.getLogin(loginName);
		logger.info("get  customer details response {} ", login);
		if (null != login && login.getLoggedInAt() != null) {

			timeElapsed = Duration.between(Instant.now(), login.getLoggedInAt().toInstant());
		}

		else
			throw new ResourceNotFoundException("002.001", "Requested login customer Not found");
		logger.info("get  customer details response {}", login);

		return timeElapsed != null && timeElapsed.toMillis() <= loginValidTimeInMilliSeconds ? login : null;
	}
	


	public LoginDTO addDefaultCustomer(CustomerDTO customerDTO, Throwable t) {
		System.out.println("Exception From fallback as Customer service is not available>>"+ t);
		return new LoginDTO();
		
	}

}
