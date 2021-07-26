package com.travel.booking.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.booking.client.RouteBusSeatClient;
import com.travel.booking.dto.BookingDTO;
import com.travel.booking.dto.BookingRouteBusSeatDTO;
import com.travel.booking.dto.CartDTO;
import com.travel.booking.dto.RouteBusSeatDTO;
import com.travel.booking.exception.ResourceNotFoundException;
import com.travel.booking.mapper.BookingMapper;
import com.travel.booking.mapper.CartMapper;
import com.travel.booking.model.Booking;
import com.travel.booking.model.BookingRouteBusSeat;
import com.travel.booking.model.Cart;
import com.travel.booking.model.RouteBusSeat;
import com.travel.booking.repository.BookingRepository;
import com.travel.booking.repository.BookingRouteBusSeatRepository;
import com.travel.booking.repository.CartRepository;
import com.travel.booking.repository.RouteBusSeatRepository;

@Service
public class BookingServiceImpl implements BookingService {

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	private RouteBusSeatRepository routeBusSeatRepository;
	
	private CartRepository cartRepository;
	
	private BookingRouteBusSeatRepository bookingRouteBusSeatRepository;
	
	private BookingRepository bookingRepository;
	
	private RouteBusSeatClient routeBusSeatClient;
	
	
	
	@Autowired	
	private BookingMapper bookingMapper;
	
	@Autowired	
	private CartMapper cartMapper;

	@Autowired
	BookingServiceImpl(RouteBusSeatRepository routeBusSeatRepository,CartRepository cartRepository,RouteBusSeatClient routeBusSeatClient,BookingRouteBusSeatRepository bookingRouteBusSeatRepository, BookingRepository bookingRepository) {
		this.routeBusSeatRepository = routeBusSeatRepository;
		this.cartRepository= cartRepository;
		this.routeBusSeatClient =routeBusSeatClient;
		this.bookingRouteBusSeatRepository = bookingRouteBusSeatRepository;
		this.bookingRepository= bookingRepository;
	}

	public List<BookingDTO> getBookings() {
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		List<Booking> bookings = bookingRepository.findAll();
		logger.info("get all Booking details response ", bookings);
		
		for(Booking booking :bookings) {
			if(null != booking) {
			logger.info("get  RouteBusSeat details response ", booking);
			CartDTO cartDTO =routeBusSeatClient.getCart(booking.getCartId());
			List<RouteBusSeatDTO> routeBusSeats = cartDTO.getSeats();
			BookingDTO bookingDTO = bookingMapper.toDto(booking);
			bookingDTO.setSeats(routeBusSeats);
			bookingDTOs.add(bookingDTO);}
		}
		
		return bookingDTOs;
	}

	public BookingDTO getBooking(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  RouteBusSeat details response ", booking);
		CartDTO cartDTO =routeBusSeatClient.getCart(booking.getCartId());
		List<RouteBusSeatDTO> routeBusSeats = cartDTO.getSeats();
		BookingDTO bookingDTO = bookingMapper.toDto(booking);
		bookingDTO.setSeats(routeBusSeats);
		return bookingDTO;
	}
	

	public RouteBusSeat updateRouteBusSeat(Long routeBusSeatId, RouteBusSeat routeBusSeat) {
		RouteBusSeat existingRouteBusSeat = routeBusSeatRepository.findById(Long.valueOf(routeBusSeatId)).orElse(null);
		logger.info("existing routeBusSeat details response ", existingRouteBusSeat);
		RouteBusSeat updatedRouteBusSeat = null;
		if (null != existingRouteBusSeat) {
			routeBusSeat.setId(existingRouteBusSeat.getId());
			logger.info("update routeBusSeat details  ", routeBusSeat);
			updatedRouteBusSeat = routeBusSeatRepository.save(routeBusSeat);
			logger.info("updated routeBusSeat details response ", updatedRouteBusSeat);
		} else
			throw new ResourceNotFoundException("002.001", "Requested resource Not found");
		return updatedRouteBusSeat != null ? updatedRouteBusSeat : null;
	}

	public void deleteRouteBusSeat(Long routeBusSeatId) {
		routeBusSeatRepository.deleteById(Long.valueOf(routeBusSeatId));
	}
	
	public CartDTO selectRouteBusSeats(List<Long> routeBusSeatList) {
		Cart existingCart = null;
		List<RouteBusSeat> updatedRouteBusSeats = new ArrayList<>();
		List<RouteBusSeat> existingRouteBusSeats = routeBusSeatList.stream()
				.map(prod -> routeBusSeatRepository.findById(prod).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
		
		logger.info("existing routeBusSeats  response {}", existingRouteBusSeats);
	
		if (null != existingRouteBusSeats) {
			 existingCart= existingRouteBusSeats.stream()
					.filter(prod -> null != prod.getCart()).findFirst().map(p->p.getCart()).orElse(null);
			if(existingCart == null) {
				existingCart = new Cart("open",  existingRouteBusSeats);
				existingCart =cartRepository.save(existingCart);
			}
			final Cart latestCart = existingCart;
			logger.info("saving routeBusSeat  : {}", latestCart);
			logger.info("existing routeBusSeats  response {}", existingRouteBusSeats);
			 updatedRouteBusSeats  =existingRouteBusSeats.stream().map(prod -> {
				prod.setCart(latestCart);
				logger.info("saving routeBusSeat  : {}", prod);
			return routeBusSeatRepository.save(prod);
			}).filter(Objects::nonNull).collect(Collectors.toList());
			
			
		}
		
		Cart cart  =updatedRouteBusSeats.stream().map(p -> p.getCart()).findFirst().orElse(null) ;
		
		CartDTO cartDTO = cartMapper.toDto(cart);
//			
		return cartDTO;
	}
	
	public Cart selectRouteBusSeats1(List<Long> routeBusSeatList) {
		Cart existingCart = null;
		List<RouteBusSeat> updatedRouteBusSeats = new ArrayList<>();
		List<RouteBusSeat> existingRouteBusSeats = routeBusSeatList.stream()
				.map(prod -> routeBusSeatRepository.findById(prod).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
		
		logger.info("existing routeBusSeats  response {}", existingRouteBusSeats);
	
		if (null != existingRouteBusSeats) {
			 existingCart= existingRouteBusSeats.stream()
					.filter(prod -> null != prod.getCart()).findFirst().map(p->p.getCart()).orElse(null);
			if(existingCart == null) {
				existingCart = new Cart("open",  existingRouteBusSeats);
				existingCart =cartRepository.save(existingCart);
			}
			final Cart latestCart = existingCart;
			logger.info("saving routeBusSeat  : {}", latestCart);
			logger.info("existing routeBusSeats  response {}", existingRouteBusSeats);
			 updatedRouteBusSeats  =existingRouteBusSeats.stream().map(prod -> {
				prod.setCart(latestCart);
				logger.info("saving routeBusSeat  : {}", prod);
			return routeBusSeatRepository.save(prod);
			}).filter(Objects::nonNull).collect(Collectors.toList());
			
			
		}
		
	
		return updatedRouteBusSeats.stream().map(p -> p.getCart()).findFirst().orElse(null);
	}
	
	public BookingDTO  placeBooking( Long cartId, Long userId) {
		List<BookingRouteBusSeat> bookingeRouteBusSeats = new ArrayList<>();
		CartDTO cartDTO =routeBusSeatClient.getCart(cartId);
		
		logger.info("existing cartDTO  : {}", cartDTO);
		List<RouteBusSeatDTO> routeBusSeats = cartDTO.getSeats();
		logger.info(" routeBusSeats  : {}", routeBusSeats);
		
		Booking booking = new Booking( cartId, "open", userId,Timestamp.from(Instant.now()),Timestamp.from(Instant.now()), cartDTO.getTotalAmount());
		
		for(RouteBusSeatDTO routeBusSeatDTO : routeBusSeats) {
			BookingRouteBusSeat bookingRouteBusSeat = new  BookingRouteBusSeat(routeBusSeatDTO.getId(), Long.valueOf(1) );			
			bookingRouteBusSeat.setBooking(booking);
			bookingeRouteBusSeats.add(bookingRouteBusSeatRepository.save(bookingRouteBusSeat));
			
		}
		
		logger.info(" bookingeRouteBusSeats details  : {}", bookingeRouteBusSeats);
		
		Booking savedBooking = bookingeRouteBusSeats.get(0).getBooking();
		BookingDTO savedBookingDTO = bookingMapper.toDto(savedBooking);
		
		savedBookingDTO.setSeats(routeBusSeats);
		
		logger.info("saved savedBookingDTO  : {}", savedBookingDTO);
		
		return savedBookingDTO;
	}
	
	public BookingDTO  updateBooking( Long bookingId, BookingDTO booking) {
		Booking updatedBooking = null;
		Booking existingBooking =bookingRepository.findById(bookingId).orElse(null);
		if(null != existingBooking) {
			existingBooking.setStatus("confirmed");
			existingBooking.setUpdatedOn(Timestamp.from(Instant.now()));
			logger.info(" bookingeRouteBusSeats details  : {}", existingBooking);
			updatedBooking = bookingRepository.save(existingBooking);
			logger.info(" updatedBooking details  : {}", updatedBooking);
		}
		
		
		return  bookingMapper.toDto(updatedBooking);
	}
	
	

}
