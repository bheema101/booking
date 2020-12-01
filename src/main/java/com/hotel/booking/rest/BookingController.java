package com.hotel.booking.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hotel.booking.model.Address;
import com.hotel.booking.model.BookingReq;
import com.hotel.booking.model.Bookinginfo;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.model.MailDto;
import com.hotel.booking.service.BookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
public class BookingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Value("${config.name:default}")
	String name = "World";
	
	@Autowired
	BookingService bookingService;
	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/bookings/{hotelName}")
	public ResponseEntity<List<Hotel>> getBookings(@PathVariable String hotelName) {
		
		LOGGER.info("---STARTED /bookings ENDPOINT--");
		List<Bookinginfo> bookinginfo = new ArrayList<>();
		//bookinginfo.add(new Bookinginfo("id", "hotel", "userName"));
		List<Hotel> getbookings = bookingService.getbookings(hotelName);
		LOGGER.info("---END bookings ENDPOINT--");
		return new ResponseEntity<List<Hotel>>(getbookings,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/save")
	public void saveBooking(@RequestBody Hotel hotel) {
		Hotel hotel2 = new Hotel();
		LOGGER.info("values as logger :---,{}",hotel);
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
	
	@PostMapping(value = "/bookhotel")
	public ResponseEntity<String> bookHotel(@RequestBody BookingReq bookreq) {
		bookingService.bookHotel(bookreq);
		return new ResponseEntity<String>("created",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/cancelBooking/{bookingid}") 
	public ResponseEntity<?> cancelBooking(@PathVariable int bookingid) {
		bookingService.cancelBooking(bookingid);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping("/welcome")
	public String getremotePropeties() {
		String forObject ="";
		try {
			forObject = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/1", String.class);
			System.out.println(forObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			MailDto mailDto = new MailDto();
			mailDto.setBookingMessage("your slot is booked succesfully Thank you for visiting");
			HttpEntity<MailDto> entity = new HttpEntity<MailDto>(mailDto);
			ResponseEntity<String> response = restTemplate.exchange("http://email-app/sendMail", HttpMethod.POST, entity, String.class);//("", String.class);
			System.out.println(forObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""+name+forObject;
		
	}
	

}
