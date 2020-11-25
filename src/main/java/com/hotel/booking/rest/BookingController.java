package com.hotel.booking.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.model.Address;
import com.hotel.booking.model.Bookinginfo;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
public class BookingController {
	@Autowired
	BookingService bookingService;
	
//	@Autowired
//	private Environment environment;
	
	@Value("${config.name:default}")
	String name = "World";
	
	static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@GetMapping(value = "/bookings")
	public ResponseEntity<List<Bookinginfo>> getBookings() {
		
		logger.info("---STARTED /bookings ENDPOINT--");
		List<Bookinginfo> bookinginfo = new ArrayList<>();
		bookinginfo.add(new Bookinginfo("id", "hotel", "userName"));
		logger.info("---END bookings ENDPOINT--");
		return new ResponseEntity<List<Bookinginfo>>(bookinginfo,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/save")
	public void saveBooking(@RequestBody Hotel hotel) {
		Hotel hotel2 = new Hotel();
		logger.info("values as logger :---,{}",hotel);
		Address address = new Address("9/1142a-56","shivanna nagar","yemmiganur","andhanrapradesh","inida","518360");
		hotel2.setHotelName("Rajeswara Hotel");
		hotel2.setCheckinTime(LocalDateTime.now());
		hotel2.setCheckoutTime(LocalDateTime.now().plusDays(1));
		hotel2.setAddress(address);
		
		bookingService.saveBooking(hotel);
	}
	
	@PostMapping(value = "/checkavialbilty")
	public ResponseEntity<List<Hotel>> getAvialiblity(@RequestBody Hotel hotel) {
		List<Hotel> avialbility = bookingService.getAvialbility(hotel);
		return new ResponseEntity<List<Hotel>>(avialbility,HttpStatus.OK);
	}
	
	@RequestMapping("/welcome")
	public String getremotePropeties() {
		
		//return environment.getProperty("spring.datasource.url");
		return ""+name;
		
	}
	

}
