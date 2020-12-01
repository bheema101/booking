package com.hotel.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.booking.dao.BookingDao;
import com.hotel.booking.model.BookingReq;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingService;

@Component
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingDao bookingdao;

	@Override
	public void saveBooking(Hotel hotel) {
		bookingdao.saveBooking(hotel);
	}
	
	@Override
	public List<Hotel> getAvialbility(Hotel hotel) {
		return bookingdao.getAvialbility(hotel);
	}

	@Override
	public List<Hotel> getbookings(String hotelName) {
		
		return bookingdao.getBookings(hotelName);
	}

	@Override
	public void bookHotel(BookingReq bookreq) {
		bookingdao.bookHotel(bookreq);
		
	}

	@Override
	public void cancelBooking(int bookingid) {
		bookingdao.cancelBooking(bookingid);
		
	}
	
	
	
	
}
